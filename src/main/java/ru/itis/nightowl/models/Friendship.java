package ru.itis.nightowl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "friendship")
@Entity
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "user_receiver")
    private User receiver;

    @Column(name = "accepted")
    private Boolean accepted;
}
