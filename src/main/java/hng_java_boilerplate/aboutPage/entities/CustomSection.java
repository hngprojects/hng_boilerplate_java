package hng_java_boilerplate.aboutPage.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "custom_section")
public class CustomSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "customSection", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private StatEntity stats;
    @OneToOne(mappedBy = "customSection", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private ServiceEntity services;
    @OneToOne
    @JoinColumn(name = "about_page_id")
    @JsonBackReference
    private AboutPage aboutPage;

}
