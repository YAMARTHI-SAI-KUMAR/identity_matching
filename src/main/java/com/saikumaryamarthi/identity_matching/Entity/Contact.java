package com.saikumaryamarthi.identity_matching.Entity;






import lombok.*;

import java.time.LocalDateTime;

// import javax.persistence.*;
import jakarta.persistence.*;
// import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {
    @Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)

    private Long id;

    private String phoneNumber;

    private String email;

    private Long linkedId; //NULLABLE , POINTS TO PRIMARY CONTACT


    @Enumerated(EnumType.STRING)
    private LinkPrecedence linkPrecedence; //PRIMARY OR SECONDARY

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

}


