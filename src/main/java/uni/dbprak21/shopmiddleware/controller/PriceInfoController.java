package uni.dbprak21.shopmiddleware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uni.dbprak21.shopmiddleware.model.PriceInfo;
import uni.dbprak21.shopmiddleware.dto.PriceInfoDTO;

@RestController
@RequestMapping("/api/v1/priceinfos") // Use "/offers" for offers endpoint
public class PriceInfoController {

    private final PriceInfoDTO priceInfoDTO; // Autowire PriceinfoDTO

    @Autowired
    public PriceInfoController(PriceInfoDTO priceInfoDTO) {
        this.priceInfoDTO = priceInfoDTO;
    }

    // Get offers for a given ASIN
    @GetMapping("/offers")
    public ResponseEntity<List<PriceInfo>> getOffers(@RequestParam("asin") String asin) {
        List<PriceInfo> offers = priceInfoDTO.getOffers(asin);
        return ResponseEntity.ok(offers);
    }
}