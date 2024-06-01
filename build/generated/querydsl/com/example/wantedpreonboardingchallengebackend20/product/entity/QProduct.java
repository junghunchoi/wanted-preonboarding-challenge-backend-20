package com.example.wantedpreonboardingchallengebackend20.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -1277698268L;

    public static final QProduct product = new QProduct("product");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> seller_id = createNumber("seller_id", Long.class);

    public final StringPath status = createString("status");

    public final ListPath<com.example.wantedpreonboardingchallengebackend20.transaction.entity.Transaction, com.example.wantedpreonboardingchallengebackend20.transaction.entity.QTransaction> transactions = this.<com.example.wantedpreonboardingchallengebackend20.transaction.entity.Transaction, com.example.wantedpreonboardingchallengebackend20.transaction.entity.QTransaction>createList("transactions", com.example.wantedpreonboardingchallengebackend20.transaction.entity.Transaction.class, com.example.wantedpreonboardingchallengebackend20.transaction.entity.QTransaction.class, PathInits.DIRECT2);

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

