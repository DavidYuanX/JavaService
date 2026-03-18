package com.example.crud.controller;

import com.example.crud.model.Banner;
import com.example.crud.repository.BannerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/banners")
public class BannerController {

    private final BannerRepository bannerRepository;

    public BannerController(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @GetMapping
    public List<Banner> getActiveBanners() {
        return bannerRepository.findByActiveTrueOrderBySortOrderAsc();
    }

    @GetMapping("/all")
    public List<Banner> getAllBanners() {
        return bannerRepository.findAllByOrderBySortOrderAsc();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Banner> getBannerById(@PathVariable Long id) {
        Banner banner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner not found with id: " + id));
        return ResponseEntity.ok(banner);
    }

    @PostMapping
    public Banner createBanner(@RequestBody Banner banner) {
        return bannerRepository.save(banner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Banner> updateBanner(@PathVariable Long id, @RequestBody Banner details) {
        Banner banner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner not found with id: " + id));

        banner.setTitle(details.getTitle());
        banner.setSubtitle(details.getSubtitle());
        banner.setImageUrl(details.getImageUrl());
        banner.setBackgroundColor(details.getBackgroundColor());
        banner.setTextColor(details.getTextColor());
        banner.setLinkUrl(details.getLinkUrl());
        banner.setSortOrder(details.getSortOrder());
        banner.setActive(details.getActive());

        return ResponseEntity.ok(bannerRepository.save(banner));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable Long id) {
        Banner banner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner not found with id: " + id));
        bannerRepository.delete(banner);
        return ResponseEntity.noContent().build();
    }
}