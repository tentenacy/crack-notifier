package com.tenutz.cracknotifier.domain.crack;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tenutz.cracknotifier.domain.robot.Robot;
import com.tenutz.cracknotifier.web.api.dto.common.CommonCondition;
import com.tenutz.cracknotifier.web.api.dto.crack.CracksResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.tenutz.cracknotifier.domain.crack.QCrack.crack;
import static com.tenutz.cracknotifier.domain.robot.QRobot.robot;

@Slf4j
@Repository
public class CrackQueryRepositoryImpl extends QuerydslRepositorySupport implements CrackQueryRepository {

    private final JPAQueryFactory queryFactory;

    public CrackQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Crack.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<CracksResponse> cracks(CommonCondition cond, Pageable pageable, Robot eRobot) {
        JPAQuery<CracksResponse> query = queryFactory.select(Projections.constructor(CracksResponse.class,
                        crack.seq,
                        crack.image,
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d %T')", crack.createdAt),
                        crack.accuracy,
                        crack.address.region3DepthName
                ))
                .from(crack)
                .join(crack.robot, robot).on(robot.eq(eRobot))
                .where(dateTimeLoe(cond.getDateTimeTo()), dateTimeGoe(cond.getDateTimeFrom()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(crack.getType(), crack.getMetadata());
            query.orderBy(new OrderSpecifier<>(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        QueryResults<CracksResponse> result = query.fetchResults();

        //카운트 쿼리 필요에 따라 날라감
        return new PageImpl(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression dateTimeLoe(LocalDateTime dateTimeLoe) {
        return dateTimeLoe != null ? crack.createdAt.loe(dateTimeLoe) : null;
    }

    private BooleanExpression dateTimeGoe(LocalDateTime dateTimeGoe) {
        return dateTimeGoe != null ? crack.createdAt.goe(dateTimeGoe) : null;
    }
}
