package blowest.kiosk.repository;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.QMenu;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static blowest.kiosk.entity.QMenu.menu;

@Repository
@RequiredArgsConstructor
public class MenuDslRepository {

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
}
