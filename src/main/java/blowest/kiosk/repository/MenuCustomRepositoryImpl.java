package blowest.kiosk.repository;


import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.status.TierStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import static blowest.kiosk.entity.QMenu.menu;

@Repository
public class MenuCustomRepositoryImpl implements MenuCustomRepository {

//    private static final TierStatus BEST = TierStatus.BEST;
    private final JPAQueryFactory jpaQueryFactory;

    public MenuCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Menu> findAllBest(TierStatus tierStatus){
        return jpaQueryFactory.selectFrom(menu)
                .where(tierStatusEq(tierStatus))
                .fetch();

    }

    private BooleanExpression tierStatusEq(TierStatus tierStatus) {
        return tierStatus != null ? menu.tierStatus.eq(TierStatus.BEST) : null;
    }


}
