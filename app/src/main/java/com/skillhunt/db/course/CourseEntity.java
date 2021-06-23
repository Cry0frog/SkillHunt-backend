package com.skillhunt.db.course;

import com.skillhunt.db.company.CompanyEntity;
import com.skillhunt.db.direction.DirectionEntity;
import com.skillhunt.db.field_area.FieldAreaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="courses")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CourseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String link;
    private String description;

    @Column(name = "image_filename")
    private String imageFilename;

    private boolean publish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direction_id", nullable=false, insertable = false, updatable = false)
    private DirectionEntity direction;

    @Column(name="direction_id")
    private Long directionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_area_id", nullable=false, insertable = false, updatable = false)
    private FieldAreaEntity fieldArea;

    @Column(name="field_area_id")
    private Long fieldAreaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable=false, insertable = false, updatable = false)
    private CompanyEntity company;

    @Column(name="company_id")
    private Long companyId;
}
