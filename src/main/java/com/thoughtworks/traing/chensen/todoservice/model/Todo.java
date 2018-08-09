package com.thoughtworks.traing.chensen.todoservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE todo SET deleted = true WHERE id = ?")
public class Todo {
    @Id
    @GeneratedValue
    private int id;

    private String content;

    @Column(name = "readonly")
    private boolean readOnly;

    private boolean complete;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "todo_id")
    private List<Task> tasks;

    private boolean visible;

    private boolean deleted;

    @Column(columnDefinition = "DATETIME")
    private Date date;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int createBy;

    private String suggestion;
}
