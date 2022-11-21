package com.example.demo.controllers.main;

import com.example.demo.models.*;
import com.example.demo.services.application.ApplicationService;
import com.example.demo.services.applicationType.ApplicationTypeService;
import com.example.demo.services.gender.GenderService;
import com.example.demo.services.groundsForFinPayment.GroundsForFinPaymentService;
import com.example.demo.services.materialPayment.MaterialPaymentService;
import com.example.demo.services.meetingMinute.MeetingMinuteService;
import com.example.demo.services.phoneNumber.PhoneNumberService;
import com.example.demo.services.publicOrganization.PublicOrganizationService;
import com.example.demo.services.unionMember.UnionMemberService;

import com.example.demo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class MainController {
    PhoneNumber newPhoneNumber;
    @Autowired
    UserService userService;
    @Autowired
    ApplicationTypeService applicationTypeService;
    @Autowired
    GenderService genderService;
    @Autowired
    UnionMemberService unionMemberService;

    @Autowired
    ApplicationService applicationService;
    @Autowired
    GroundsForFinPaymentService groundsForFinPaymentService;
    @Autowired
    MaterialPaymentService materialPaymentService;
    @Autowired
    MeetingMinuteService meetingMinuteService;
    @Autowired
    PhoneNumberService phoneNumberService;
    @Autowired
    PublicOrganizationService publicOrganizationService;


    @GetMapping({"/mainPage/index"})
    public String mainPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        model.addAttribute("updateBook", new UnionMember());
        model.addAttribute("books", applicationTypeService.readAll());
        return "mainPage/index";
    }

    @GetMapping({"/clientsPage/index"})
    public String clientsPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("clients", genderService.readAll());
        model.addAttribute("updateClient", new Child());
        return "clientsPage/index";
    }

    @GetMapping({"/suppliesPage/index"})
    public String suppliesPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("supplies", unionMemberService.readAll());
        return "suppliesPage/index";
    }

    @GetMapping({"/newSupplyPage/index"})
    public String newSupply(Model model, Principal user) {
        if (newPhoneNumber == null) {
            newPhoneNumber = new PhoneNumber();
        }
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("newSupply", newPhoneNumber);
        return "newSupplyPage/index";
    }

    @PostMapping({"/newSupplyPage/index"})
    public String newSupply(Model model, @ModelAttribute("newSupply") PhoneNumber newPhoneNumber, Principal user) {
        this.newPhoneNumber = newPhoneNumber;
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        saveSupply(this.newPhoneNumber);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/newSupplyPage/index/addBook"})
    public String addBookToSupply(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        newPhoneNumber.getSupplyDetails().add(new SupplyDetail());
        model.addAttribute("newSupply", newPhoneNumber);
        return "redirect:/newSupplyPage/index";
    }

    @GetMapping({"/newSupplyPage/index/removeBook"})
    public String removeBookFromSupply(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        if (newPhoneNumber.getSupplyDetails().size() > 1) {
//            newPhoneNumber.getSupplyDetails().remove(newPhoneNumber.getSupplyDetails().size() - 1);
//        }
        model.addAttribute("newSupply", newPhoneNumber);
        return "redirect:/newSupplyPage/index";
    }

    @PostMapping("/mainPage/index/update/{id}")
    public String updateBook(Model model, @ModelAttribute("updateBook") UnionMember updateUnionMember, Principal user, @PathVariable("id") Long bookId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        UnionMember unionMember = applicationTypeService.readById(bookId);
//        updateBook(unionMember, updateUnionMember);
//        applicationTypeService.update(unionMember, bookId);
        return "redirect:/mainPage/index";
    }

    @GetMapping("/mainPage/index/delete/{id}")
    public String deleteBook(Model model, Principal user, @PathVariable("id") Long bookId) {
//        applicationTypeService.delete(bookId);
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        return "redirect:/mainPage/index";
    }

//
//    public MaterialPayment savePublisher(MaterialPayment materialPayment) {
//        MaterialPayment someMaterialPayment = publisherService.readByPublisherTitle(materialPayment.getPublisherTitle());
//        if (someMaterialPayment == null)
//            return publisherService.create(materialPayment);
//        else
//            return someMaterialPayment;
//    }
//
//    public Position saveAuthor(Position position) {
//        Position somePosition = authorService.readByAuthorName(position.getAuthorName());
//        if (somePosition == null)
//            return authorService.create(position);
//        else
//            return somePosition;
//    }
//
//    public Gender saveGenre(Gender genre) {
//        Gender someGenre = genreService.readByGenreName(genre.getGenreName());
//        if (someGenre == null)
//            return genreService.create(genre);
//        else
//            return someGenre;
//    }
//
//    public ApplicationType saveIsbn(UnionMember unionMember) {
//        unionMember.getApplicationType().generateIsbnNumber(unionMember);
//        ApplicationType someApplicationType = isbnService.readByIsbnNumber(unionMember.getApplicationType().getIsbnNumber());
//        if (someApplicationType == null)
//            return isbnService.create(unionMember.getApplicationType());
//        else
//            return someApplicationType;
//    }
//
//    public GroundsForFinPayment saveLanguage(GroundsForFinPayment groundsForFinPayment) {
//        GroundsForFinPayment someGroundsForFinPayment = languageService.readByLanguageName(groundsForFinPayment.getLanguageName());
//        if (someGroundsForFinPayment == null)
//            return languageService.create(groundsForFinPayment);
//        else
//            return someGroundsForFinPayment;
//    }
//
//    public void updateBook(UnionMember unionMember, UnionMember updateUnionMember){
//        unionMember.setTitle(updateUnionMember.getTitle().toLowerCase().trim());
//        unionMember.setPosition(saveAuthor(updateUnionMember.getPosition()));
//        unionMember.setGenre(saveGenre(updateUnionMember.getGenre()));
//        unionMember.setApplicationType(saveIsbn(updateUnionMember));
//        unionMember.setGroundsForFinPayment(saveLanguage(updateUnionMember.getGroundsForFinPayment()));
//        unionMember.setMaterialPayment(savePublisher(updateUnionMember.getMaterialPayment()));
//    }
//
//    public UnionMember saveBook(UnionMember unionMember) {
//        unionMember.setPosition(saveAuthor(unionMember.getPosition()));
//        unionMember.setGenre(saveGenre(unionMember.getGenre()));
//        unionMember.setApplicationType(saveIsbn(unionMember));
//        unionMember.setGroundsForFinPayment(saveLanguage(unionMember.getGroundsForFinPayment()));
//        unionMember.setMaterialPayment(savePublisher(unionMember.getMaterialPayment()));
//        UnionMember someUnionMember = bookService.readByBookTitle(unionMember.getTitle());
//        if (someUnionMember == null || !someUnionMember.equals(unionMember))
//            return bookService.create(unionMember);
//        else
//            return someUnionMember;
//    }
//
//    public void saveSupplyDetail(PhoneNumber phoneNumber) {
//        List<SupplyDetail> supplyDetails = phoneNumber.getSupplyDetails();
//        for (int i = 0; i < supplyDetails.size(); i++) {
//            supplyDetails.get(i).setPhoneNumber(phoneNumber);
//            supplyDetails.get(i).setUnionMember(saveBook(supplyDetails.get(i).getUnionMember()));
//            supplyDetails.set(i, supplyDetailService.create(supplyDetails.get(i)));
//        }
//    }
//
//    public MeetingMinute saveSupplier(MeetingMinute meetingMinute) {
//        MeetingMinute someMeetingMinute = supplierService.readBySupplierName(meetingMinute.getSupplierName());
//        if (someMeetingMinute == null)
//            return supplierService.create(meetingMinute);
//        else
//            return someMeetingMinute;
//    }
//
//    public void saveSupply(PhoneNumber phoneNumber) {
//        phoneNumber.setMeetingMinute(saveSupplier(phoneNumber.getMeetingMinute()));
//        saveSupplyDetail(supplyService.create(phoneNumber));
//    }
}
