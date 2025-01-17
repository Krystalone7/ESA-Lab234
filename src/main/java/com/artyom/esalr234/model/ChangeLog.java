package com.artyom.esalr234.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "changelog")
public class ChangeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "change_type", nullable = false)
    private String changeType;

    @Column(name = "entity_class", nullable = false)
    private String entityClass;

    @Column(name = "change_details")
    private String changeDetails;

    @Column(name = "change_timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp changeTimestamp;

    @Override
    public String toString() {
        return "ChangeLog{" +
                "id=" + id +
                ", changeType='" + changeType + '\'' +
                ", entityClass='" + entityClass + '\'' +
                ", changeDetails='" + changeDetails + '\'' +
                ", changeTimestamp=" + changeTimestamp +
                '}';
    }
}