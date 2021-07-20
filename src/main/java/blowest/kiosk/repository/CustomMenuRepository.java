package blowest.kiosk.repository;

import blowest.kiosk.entity.Menu;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static blowest.kiosk.entity.QMenu.menu;

@Repository
public class CustomMenuRepository {

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    @PostConstruct
    void initialize() {
        queryFactory = new JPAQueryFactory(em);
    }

    public List<Menu> findAllMenu() {
        return queryFactory.select(menu)
                .from(menu)
                .orderBy(menu.id.asc())
                .fetch();
    }

    public QueryResults<Menu> findMenusWithPagination(Long topMenuId, int offset, int pageSize) {
        return queryFactory.selectFrom(menu)
                .where(menu.topMenu.id.eq(topMenuId))
                .orderBy(menu.id.asc())
                .offset(offset)
                .limit(pageSize)
                .fetchResults();
    }
}
