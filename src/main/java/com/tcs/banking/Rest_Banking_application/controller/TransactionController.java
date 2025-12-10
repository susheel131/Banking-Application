package com.tcs.banking.Rest_Banking_application.controller;

 
import com.tcs.banking.Rest_Banking_application.entity.Account;
import com.tcs.banking.Rest_Banking_application.entity.Transaction;
import com.tcs.banking.Rest_Banking_application.entity.User;
import com.tcs.banking.Rest_Banking_application.service.TransactionService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller  
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @GetMapping("/accountInfo")
    public String showPage() {
        return "account-entry";
    }
    
    @GetMapping("/dashboard")
    public String bankingdashboard( Model model) {
        model.addAttribute("message", "You are logged in Successfully!");
        return "dashboard";
        
    }
    
    @GetMapping("/deposit")
    public String depositView() {
        return "deposit";
    }
    
    @GetMapping("/withdraw")  
    public  String withdrawView() {
        return "withdraw";
    }
    
    @GetMapping("/transfer")  
    public String transferView() {
        return "transfer";
    }
    
    @PostMapping("/deposit")
    public String deposit(@RequestParam String accountNumber, @RequestParam double amount, RedirectAttributes redirectAttributes ) {
  
    	
        transactionService.deposit(accountNumber, amount);
        redirectAttributes.addFlashAttribute("successMessage",
                "₹" + amount + " Deposited Successfully!");
        
        return "redirect:/dashboard";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam String accountNumber, @RequestParam double amount,
    		@RequestParam String password,
    		RedirectAttributes redirectAttributes) {
       
        transactionService.withdraw( accountNumber, amount, password);
        redirectAttributes.addFlashAttribute("successMessage",
                "₹" + amount + " Withdraw Successfully!");
        return "redirect:/dashboard";
    }

    
    @PostMapping("/transfer")
    public String transferAmount(@RequestParam String senderAccountNumber, 
                                 @RequestParam String receiverAccountNumber,  
                                 @RequestParam double amount , @RequestParam String password,
                                 RedirectAttributes redirectAttributes) {
    	
    	
    	redirectAttributes.addFlashAttribute("successMessage",
                "₹" + amount + " Transfer Successfully!");
    	

        transactionService.transfer(senderAccountNumber, receiverAccountNumber, amount, password);
        
      
        
        return "redirect:/dashboard"; 
    }
    
    
    
    
    
  

    @PostMapping("/accountInfo")
    public String loadAccount(@RequestParam String accountNumber, Model model) {

        Account account = transactionService.getAccount(accountNumber);
        User user = account.getUser();
        List<Transaction> transactions = transactionService.getTransactions(accountNumber);

        model.addAttribute("user", user);
        model.addAttribute("account", account);
        model.addAttribute("transactions", transactions);

        return "accountInfo";
    }

    
    
    
}