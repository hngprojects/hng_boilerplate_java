package hng_java_boilerplate.aboutpage.controller;

import hng_java_boilerplate.aboutpage.dto.AboutPageContentDto;
import hng_java_boilerplate.aboutpage.dto.ApiResponse;
import hng_java_boilerplate.aboutpage.service.AboutPageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/content")
@RequiredArgsConstructor
public class AboutPageController {
    private final AboutPageService aboutPageService;

    @PutMapping("/about")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> updateAboutPageContent(@Valid @RequestBody AboutPageContentDto contentDto) {
        try {
            aboutPageService.updateAboutPageContent(contentDto);
            return ResponseEntity.ok(new ApiResponse("About page content updated successfully.", 200));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to update About page content.", 500));
        }
    }

    @DeleteMapping("/about")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> deleteAboutPageContent() {
        try {
            aboutPageService.deleteAboutPageContent();
            return ResponseEntity.ok(new ApiResponse("About page content deleted successfully.", 200));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to delete About page content.", 500));
        }
    }
}
