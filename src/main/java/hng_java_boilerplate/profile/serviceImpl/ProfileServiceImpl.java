package hng_java_boilerplate.profile.serviceImpl;

import hng_java_boilerplate.exception.BadRequestException;
import hng_java_boilerplate.profile.dto.request.DeactivateUserRequest;
import hng_java_boilerplate.profile.dto.request.UpdateUserProfileDto;
import hng_java_boilerplate.profile.dto.response.DeactivateUserResponse;
import hng_java_boilerplate.profile.dto.response.ProfileUpdateResponseDto;
import hng_java_boilerplate.profile.entity.Profile;
import hng_java_boilerplate.profile.exceptions.InternalServerErrorException;
import hng_java_boilerplate.profile.exceptions.NotFoundException;
import hng_java_boilerplate.profile.repository.ProfileRepository;
import hng_java_boilerplate.profile.service.ProfileService;
import hng_java_boilerplate.user.entity.User;
import hng_java_boilerplate.user.repository.UserRepository;
import hng_java_boilerplate.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Override
    public DeactivateUserResponse deactivateUser(DeactivateUserRequest request) {
        User authUser = userService.getLoggedInUser();

        String confirmation = request.getConfirmation().toLowerCase();

        if (confirmation.equals("true") && authUser.getIsDeactivated()) {
            throw new BadRequestException("User has been deactivated");
        }

        if (!confirmation.equals("true")) throw new BadRequestException("Confirmation needs to be true for deactivation");

        authUser.setIsDeactivated(true);
        userRepository.save(authUser);

        //todo: call email service to notify user of account deactivated

        return new DeactivateUserResponse(200, "Account Deactivated Successfully");
    }

    @Override
    public Optional<?> updateUserProfile(String id, UpdateUserProfileDto updateUserProfileDto) {
        try{
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()){
                Profile profile = user.get().getProfile();

                profile.setFirstName(updateUserProfileDto.getFirstName());
                profile.setLastName(updateUserProfileDto.getLastName());
                profile.setJobTitle(updateUserProfileDto.getJobTitle());
                profile.setPronouns(updateUserProfileDto.getPronouns());
                profile.setJobTitle(updateUserProfileDto.getJobTitle());
                profile.setDepartment(updateUserProfileDto.getDepartment());
                profile.setSocial(updateUserProfileDto.getSocial());
                profile.setBio(updateUserProfileDto.getBio());
                profile.setPhone(updateUserProfileDto.getPhoneNumber());
                profile.setAvatarUrl(updateUserProfileDto.getAvatarUrl());

                profile = profileRepository.save(profile);
                return Optional.of( ProfileUpdateResponseDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Profile updated successfully")
                        .data(profile)
                        .build()
                );
            }else {
                throw new NotFoundException("User not found");
            }
        }catch (NotFoundException e) {
            throw e;
        }catch (Exception e){
            throw new InternalServerErrorException("An unexpected error occurred");
        }
    }
}