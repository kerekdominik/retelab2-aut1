package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Note;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Random;

@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public String save(Ad ad) {
        ad.setKey(SecretGenerator.generate());
        em.merge(ad);
        return ad.getKey();
    }

    public List<Ad> findByPrice(Double min, Double max) {
        return em.createQuery("SELECT ad FROM Ad ad WHERE ad.price <= ?1 AND ad.price >= ?2", Ad.class).setParameter(1, max).setParameter(2, min).getResultList();
    }

    public Ad findById(long id) {
        return em.find(Ad.class, id);
    }

    @Transactional
    public Ad editWithKey(Ad ad) {
        if (ad.getKey().equals(findById(ad.getId()).getKey())) {
            return em.merge(ad);
        }
        return null;
    }

    public static class SecretGenerator {
        private static final char[] CHARS =
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        private static final Random RND = new Random();

        public static String generate() {
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < 6; i++)
                sb.append(CHARS[RND.nextInt(CHARS.length)]);
            return sb.toString();
        }
    }

    public List<Ad> findByTag(String tag) {
        return em.createQuery("SELECT ad FROM Ad ad WHERE ?1 MEMBER OF ad.tags", Ad.class).setParameter(1, tag).getResultList();
    }
}
