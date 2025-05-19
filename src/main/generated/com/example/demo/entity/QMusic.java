package com.example.demo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMusic is a Querydsl query type for Music
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMusic extends EntityPathBase<Music> {

    private static final long serialVersionUID = -154616652L;

    public static final QMusic music = new QMusic("music");

    public final StringPath albumName = createString("albumName");

    public final StringPath artist = createString("artist");

    public final StringPath content = createString("content");

    public final StringPath genre = createString("genre");

    public final StringPath lyrics = createString("lyrics");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final StringPath title = createString("title");

    public QMusic(String variable) {
        super(Music.class, forVariable(variable));
    }

    public QMusic(Path<? extends Music> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMusic(PathMetadata metadata) {
        super(Music.class, metadata);
    }

}

