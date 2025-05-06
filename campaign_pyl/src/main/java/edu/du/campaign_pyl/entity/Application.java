package edu.du.campaign_pyl.entity;

import edu.du.campaign_pyl.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "campaign_no", nullable = false)
    private Campaign campaign;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_no", nullable = false)
    private Users users;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
