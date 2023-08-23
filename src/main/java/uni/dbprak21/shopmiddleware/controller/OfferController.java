package uni.dbprak21.shopmiddleware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uni.dbprak21.shopmiddleware.model.PriceInfo;
import uni.dbprak21.shopmiddleware.dto.OfferDTO;
import uni.dbprak21.shopmiddleware.model.Product;

@RestController
public class OfferController {

    private final OfferDTO offerDTO; // Autowire PriceinfoDTO

    @Autowired
    public OfferController(OfferDTO offerDTO) {
        this.offerDTO = offerDTO;
    }

    // Get offers for a given ASIN
    public ResponseEntity<List<PriceInfo>> getOffers(String asin) {
        List<PriceInfo> offers = offerDTO.getOffers(asin);
        return ResponseEntity.ok(offers);
    }

    // Get offers for a given ASIN
    public ResponseEntity<List<Product>> getSimilarCheaperProduct(String asin) {
        List<Product> offers = offerDTO.getSimilarCheaperProduct(asin);
        return ResponseEntity.ok(offers);
    }
}