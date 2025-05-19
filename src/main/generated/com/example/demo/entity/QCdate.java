package com.example.demo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCdate is a Querydsl query type for Cdate
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QCdate extends EntityPathBase<Cdate> {

    private static final long serialVersionUID = -164375264L;

    public static final QCdate cdate = new QCdate("cdate");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public QCdate(String variable) {
        super(Cdate.class, forVariable(variable));
    }

    public QCdate(Path<? extends Cdate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCdate(PathMetadata metadata) {
        super(Cdate.class, metadata);
    }

}

