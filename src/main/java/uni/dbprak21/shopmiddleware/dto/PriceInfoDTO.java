package uni.dbprak21.shopmiddleware.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import uni.dbprak21.shopmiddleware.ShopMiddlewareInterface;
import uni.dbprak21.shopmiddleware.model.PriceInfo;

@Component
public class PriceInfoDTO implements ShopMiddlewareInterface {

    private final EntityManager entityManager;

    @Autowired
    public PriceInfoDTO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<PriceInfo> getOffers(String asin) {
        String HQL = "FROM PriceInfo pi WHERE pi.product.asin = :asin";
        TypedQuery<PriceInfo> query = entityManager.createQuery(HQL, PriceInfo.class);
        query.setParameter("asin", asin);
        return query.getResultList();
    }
}