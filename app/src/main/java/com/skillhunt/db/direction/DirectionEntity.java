package com.skillhunt.db.direction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="direction")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class DirectionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "direction_name")
    private String directionName;
}
