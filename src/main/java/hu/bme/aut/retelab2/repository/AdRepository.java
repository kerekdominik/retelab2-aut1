package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Ad;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ad save(Ad ad) { return em.merge(ad); }

    public List<Ad> findByPrice(Double min, Double max) {
        return em.createQuery("SELECT ad FROM Ad ad WHERE ad.price <= ?1 AND ad.price >= ?2", Ad.class).setParameter(1, max).setParameter(2, min).getResultList();
    }
}
