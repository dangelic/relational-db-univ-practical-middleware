package uni.dbprak21.shopmiddleware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uni.dbprak21.shopmiddleware.ShopMiddleware;
import uni.dbprak21.shopmiddleware.model.PriceInfo;
import uni.dbprak21.shopmiddleware.dto.OfferDTO;
import uni.dbprak21.shopmiddleware.model.Product;

@RestController
public class OfferController  {

    private final OfferDTO offerDTO;

    @Autowired
    public OfferController(OfferDTO offerDTO) {
        this.offerDTO = offerDTO;
    }

    public ResponseEntity<List<PriceInfo>> getOffers(String productId) {
        List<PriceInfo> offers = offerDTO.getOffers(productId);
        return ResponseEntity.ok(offers);
    }

    public ResponseEntity<List<Product>> getSimilarCheaperProduct(String productId) {
        List<Product> offers = offerDTO.getSimilarCheaperProduct(productId);
        return ResponseEntity.ok(offers);
    }
}