package com.example.demo.controllers.main;

import com.example.demo.models.*;
import com.example.demo.services.application.ApplicationService;
import com.example.demo.services.applicationType.ApplicationTypeService;
import com.example.demo.services.child.ChildService;
import com.example.demo.services.gender.GenderService;
import com.example.demo.services.groundsForFinPayment.GroundsForFinPaymentService;
import com.example.demo.services.materialPayment.MaterialPaymentService;
import com.example.demo.services.meetingMinute.MeetingMinuteService;
import com.example.demo.services.phoneNumber.PhoneNumberService;
import com.example.demo.services.position.PositionService;
import com.example.demo.services.publicOrganization.PublicOrganizationService;
import com.example.demo.services.unionMember.UnionMemberService;

import com.example.demo.services.user.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    @Autowired
    PositionService positionService;
    @Autowired
    ChildService childService;

    @GetMapping({"/mainPage/index"})
    public String mainPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("newUnionMember", new UnionMember());
        model.addAttribute("genders", genderService.readAll());
        model.addAttribute("positions", positionService.readAll());
        model.addAttribute("unionMembers", unionMemberService.readAll());
        return "mainPage/index";
    }

    @PostMapping("/mainPage/index/add")
    public String mainPageAdd(Model model, @ModelAttribute("newUnionMember") UnionMember newUnionMember, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        newUnionMember.setGender(genderService.readById(newUnionMember.getGender().getGenderId()));
        if (newUnionMember.getPosition().getPositionId() != null)
            newUnionMember.setPosition(positionService.readById(newUnionMember.getPosition().getPositionId()));
        newUnionMember.getPhoneNumbers().get(0).setUnionMember(newUnionMember);
        unionMemberService.create(newUnionMember);
        savePhoneNumber(newUnionMember);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/childrenPage/index"})
    public String childrenPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("genders", genderService.readAll());
        model.addAttribute("newChild", new Child());
        model.addAttribute("unionMembers", unionMemberService.readAll());
        model.addAttribute("parentsChildren", getParentChildList());
        return "childrenPage/index";
    }

    @PostMapping("/childrenPage/index/add")
    public String childrenPageAdd(Model model, @ModelAttribute("newChild") Child newChild, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        Set<UnionMember> unionMembers = newChild.getUnionMembers();
        newChild.getUnionMembers().add(unionMemberService.readById(newChild.getChildId()));
        newChild.setGender(genderService.readById(newChild.getGender().getGenderId()));
        newChild.setChildId(null);
        childService.create(newChild);
        return "redirect:/childrenPage/index";
    }

    @GetMapping({"/unionMemPubOrgPage/index"})
    public String unionMemPubOrgPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("unionMembersPublicOrganizations", getPublicOrgUnionMemberList());
        model.addAttribute("unionMembers", unionMemberService.readAll());
        model.addAttribute("newMemberOrg", new PublicOrgUnionMember());
        model.addAttribute("publicOrganizations", publicOrganizationService.readAll());
        return "unionMemPubOrgPage/index";
    }

    @PostMapping({"/unionMemPubOrgPage/index/add"})
    public String unionMemPubOrgPageAdd(Model model, @ModelAttribute("newMemberOrg") PublicOrgUnionMember newMemberOrg, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        UnionMember unionMember = unionMemberService.readById(newMemberOrg.getUnionMember().getUnionMemberId());
        unionMember.getPublicOrganizations().add(newMemberOrg.getPublicOrganization());
        unionMemberService.update(unionMember.getUnionMemberId(), unionMember);
        return "redirect:/unionMemPubOrgPage/index";
    }

    @GetMapping({"/publicOrganizationsPage/index"})
    public String publicOrganizationsPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("publicOrganizations", publicOrganizationService.readAll());
        model.addAttribute("newPublicOrganization", new PublicOrganization());
        return "publicOrganizationsPage/index";
    }

    @PostMapping("/publicOrganizationsPage/index/add")
    public String publicOrganizationsPageAdd(Model model, @ModelAttribute("newPublicOrganization") PublicOrganization newPublicOrganization, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        publicOrganizationService.create(newPublicOrganization);
        return "redirect:/publicOrganizationsPage/index";
    }

    @GetMapping({"/positionsPage/index"})
    public String positionsPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("newPosition", new Position());
        model.addAttribute("positions", positionService.readAll());
        return "positionsPage/index";
    }

    @PostMapping("/positionsPage/index/add")
    public String positionsPageAdd(Model model, @ModelAttribute("newPosition") Position newPosition, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        positionService.create(newPosition);
        return "redirect:/positionsPage/index";
    }

    @GetMapping({"/unionMembersApplicationsPage/index"})
    public String unionMembersApplicationsPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("applications", applicationService.readAll());
        model.addAttribute("newApplication", new Application());
        model.addAttribute("unionMembers", unionMemberService.readAll());
        model.addAttribute("applicationTypes", applicationTypeService.readAll());
        model.addAttribute("materialPayments", materialPaymentService.readAll());
        model.addAttribute("meetingMinutes", meetingMinuteService.readAll());
        return "unionMembersApplicationsPage/index";
    }

    @PostMapping("/unionMembersApplicationsPage/index/add")
    public String unionMembersApplicationsPageAdd(Model model, @ModelAttribute("newApplication") Application newApplication, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        applicationService.create(newApplication);
        return "redirect:/unionMembersApplicationsPage/index";
    }

    @GetMapping({"/applicationTypesPage/index"})
    public String applicationTypesPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("applicationTypes", applicationTypeService.readAll());
        model.addAttribute("newApplicationType", new ApplicationType());
        return "applicationTypesPage/index";
    }

    @PostMapping("/applicationTypesPage/index/add")
    public String applicationTypesPageAdd(Model model, @ModelAttribute("newApplicationType") ApplicationType newApplicationType, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        applicationTypeService.create(newApplicationType);
        return "redirect:/applicationTypesPage/index";
    }

    @GetMapping({"/paymentsAmountPage/index"})
    public String paymentsAmountPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("materialPayments", materialPaymentService.readAll());
        model.addAttribute("grounds", groundsForFinPaymentService.readAll());
        model.addAttribute("newMaterialPayment", new MaterialPayment());
        return "paymentsAmountPage/index";
    }

    @PostMapping("/paymentsAmountPage/index/add")
    public String paymentsAmountPageAdd(Model model, @ModelAttribute("newMaterialPayment") MaterialPayment newMaterialPayment, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        newMaterialPayment.setGroundsForFinPayment(groundsForFinPaymentService.readById(newMaterialPayment.getGroundsForFinPayment().getGroundId()));
        materialPaymentService.create(newMaterialPayment);
        return "redirect:/paymentsAmountPage/index";
    }

    @GetMapping({"/groundsForFinPayPage/index"})
    public String groundsForFinPayPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("groundsForFinPayments", groundsForFinPaymentService.readAll());
        model.addAttribute("newPayGround", new GroundsForFinPayment());
        return "groundsForFinPayPage/index";
    }

    @PostMapping("/groundsForFinPayPage/index/add")
    public String groundsForFinPayPageAdd(Model model, @ModelAttribute("newPayGround") GroundsForFinPayment newPayGround, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        groundsForFinPaymentService.create(newPayGround);
        return "redirect:/groundsForFinPayPage/index";
    }

    @GetMapping({"/meetingMinutesPage/index"})
    public String meetingMinutesPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("newMeetingMinute", new MeetingMinute());
        model.addAttribute("meetingMinutes", meetingMinuteService.readAll());
        return "meetingMinutesPage/index";
    }

    @PostMapping("/meetingMinutesPage/index/add")
    public String meetingMinutesPageAdd(Model model, @ModelAttribute("newMeetingMinute") MeetingMinute newMeetingMinute, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        meetingMinuteService.create(newMeetingMinute);
        return "redirect:/meetingMinutesPage/index";
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

    public List<ParentChild> getParentChildList() {
        List<UnionMember> unionMembers = unionMemberService.readAll();
        List<ParentChild> parentsChildren = new ArrayList<>();
        for (UnionMember unionMember : unionMembers) {
            Set<Child> children = unionMember.getChildren();
            for (Child child : children) {
                parentsChildren.add(new ParentChild(unionMember, child));
            }
        }
        return parentsChildren;
    }

    public List<PublicOrgUnionMember> getPublicOrgUnionMemberList(){
        List<PublicOrganization> publicOrganizations = publicOrganizationService.readAll();
        List<PublicOrgUnionMember> publicOrgUnionMemberList = new ArrayList<>();
        for(PublicOrganization publicOrganization : publicOrganizations){
            Set<UnionMember> unionMembers = publicOrganization.getUnionMembers();
            for(UnionMember unionMember : unionMembers){
                publicOrgUnionMemberList.add(new PublicOrgUnionMember(publicOrganization, unionMember));
            }
        }
        return publicOrgUnionMemberList;
    }

    public PhoneNumber savePhoneNumber(UnionMember unionMember) {
        return phoneNumberService.create(unionMember.getPhoneNumbers().get(0));
    }

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
