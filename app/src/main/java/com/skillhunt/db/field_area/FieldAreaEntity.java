package com.skillhunt.db.field_area;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="fieldArea")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class FieldAreaEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "field_area")
    private String fieldArea;
}
