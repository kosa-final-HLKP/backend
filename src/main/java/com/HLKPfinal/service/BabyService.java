//package com.HLKPfinal.service;
//
//import com.HLKPfinal.entity.Baby;
//import com.HLKPfinal.entity.Member;
//import com.HLKPfinal.repository.BabyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class BabyService {
//
//    private final BabyRepository babyRepository;
//
//    @Autowired
//    public BabyService(BabyRepository babyRepository) {
//        this.babyRepository = babyRepository;
//    }
//
//    public Baby addBaby(String name, Date birth, byte[] etc, Member member) {
//        Baby baby = new Baby(name, birth, etc);
//        baby.setMember(member);
//        return babyRepository.save(baby);
//    }
//
//    public List<Baby> getAllBabies() {
//        return babyRepository.findAll();
//    }
//
//    public List<Baby> getBabiesByMember(Member member) {
//        return babyRepository.findByMember(member);
//    }
//}