package com.valhalla.ehrplugin.elation.domain;

import jakarta.persistence.*;

@Entity
@Table(name="kafka_consumer")
public class KafkaConsumer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="group_name", length=50, nullable=false, unique=false)
    private String groupName;

    @Column(name="group", length=50, nullable=false, unique=false)
    private String gr;
    @Column(name="topic_name", length=50, nullable=false, unique=false)
    private String topicName;

    @Column(name="object_value", length=20000, nullable=false, unique=false)
    private String objectValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(String objectValue) {
        this.objectValue = objectValue;
    }
}
