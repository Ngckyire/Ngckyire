package com.lizhi.assignment;

import com.lizhi.assignment.exception.BusinessException;
import com.lizhi.assignment.exception.LoginException;
import com.lizhi.assignment.exception.NotLoanAccountException;
import com.lizhi.assignment.exception.NotSuchBusinessException;

import java.io.*;
import java.util.*;

public class Bank {
    private static List<Account> accounts;

    private int count;

    private Scanner scanner=new Scanner(System.in);

    private BufferedReader br=new BufferedReader(new FileReader("accounts.txt"));

    private BufferedWriter bw=new BufferedWriter(new FileWriter("accounts.txt",true));

    public static Bank BANK;

//    static {
//        accounts=new ArrayList<>();
//        Account account1 = new CreditAccount();
//        account1.setName("lz");
//        account1.setBalance(1234.12);
//        account1.setPersonId("342425200205220812");
//        account1.setPassword("asd");
//        account1.setEmail("123@qq.com");
//        accounts.add(account1);
//
//        LoanCreditAccount account11 = new LoanCreditAccount();
//        account11.setName("lz");
//        account11.setBalance(1234.12);
//        account11.setCeiling(1230);
//        account11.setPersonId("342425200205220812");
//        account11.setPassword("asd");
//        account11.setEmail("123@qq.com");
//        accounts.add(account11);
//
//        Account account2 = new CreditAccount();
//        account2.setName("lz");
//        account2.setBalance(2187.12);
//        account2.setPersonId("342425200205220813");
//        account2.setPassword("asd");
//        account2.setEmail("123@qq.com");
//        accounts.add(account2);
//
//        Account account3 = new CreditAccount();
//        account3.setName("lz");
//        account3.setBalance(124.12);
//        account3.setPersonId("342425200205220814");
//        account3.setPassword("asd");
//        account3.setEmail("123@qq.com");
//        accounts.add(account3);
//
//    }

    private Bank() throws IOException {
        accounts=new ArrayList<>();
        this.count=accounts.size();
        for(Account account:accounts){
            saveAccount(account);
        }
    }

    public static Bank getInstance() throws IOException {
        if(BANK==null){
            BANK=new Bank();
        }
        return BANK;
    }

    public Account addAccount() throws BusinessException, IOException {
        System.out.println("请输入您想办理储蓄卡、信用卡、贷款储蓄卡、贷款信用卡？1/2/3/4");
        Account account;
        String temp;
        temp=scanner.nextLine();
        if(temp.equals("1")){
            account=new SavingAccount();
        }else if(temp.equals("2")) {
            account=new CreditAccount();
        }else if(temp.equals("3")){
            account=new LoanSavingAccount();
        }else if(temp.equals("4")){
            account=new LoanCreditAccount();
        }else {
            throw new NotSuchBusinessException(BusinessException.NOT_SUCH_BUSINESS_ERROR_CODE,"不存在此业务！");
        }
        System.out.println("请输入户主名称：");
        temp = scanner.nextLine();
        while(temp.equals("")||temp==null){
            System.out.println("户主名称不得为空，请重新输入户主名称：");
            temp=scanner.nextLine();
        }
        account.setName(temp);

        System.out.println("请输入您的身份证号：");
        temp = scanner.nextLine();
        while(temp.equals("")||temp==null||temp.length()!=18){
            System.out.println("身份证输入错误，请重新输入：");
            temp = scanner.nextLine();
        }
        account.setPersonId(temp);

        System.out.println("请输入您的密码：");
        temp=scanner.nextLine();
        boolean flag=true;
        while(flag){
            while(temp.equals("")){
                System.out.println("请重新输入密码：");
                temp=scanner.nextLine();
                continue;
            }
            System.out.println("请确认密码：");
            if(temp.equals(scanner.nextLine())){
                flag=false;
            }else {
                throw new LoginException(BusinessException.REGISTER_ERROR_CODE,"确认密码错误，请重新输入密码：");
            }
        }
        account.setPassword(temp);

        System.out.println("请输入您的邮箱：");
        temp=scanner.nextLine();
        while(temp.equals("")||!temp.contains("@")||!temp.endsWith("com")){
            System.out.println("输入邮箱有误，请重新输入邮箱：");
            temp=scanner.nextLine();
        }
        account.setEmail(temp);

        System.out.println("请输入存入金额：");
        temp=scanner.nextLine();
        while(temp.equals("")||Double.parseDouble(temp)<0){
            System.out.println("输入金额有误，请重新输入存入金额：");
            temp=scanner.nextLine();
        }
        account.setBalance(Double.parseDouble(temp));

        if(account instanceof CreditAccount){
            System.out.println("请输入透支额度：");
            temp=scanner.nextLine();
            while(temp.equals("")||Double.parseDouble(temp)<0){
                System.out.println("输入金额有误，请重新输入存入金额：");
                temp=scanner.nextLine();
            }
            ((CreditAccount) account).setCeiling(Double.parseDouble(temp));
        }
        accounts.add(account);
        saveAccount(account);
        this.count=accounts.size();
        return account;
    }

    public Account getAccount(long id){
        for(int i=0;i< accounts.size();i++){
            if(accounts.get(i).getId()==id){
                return accounts.get(i);
            }
        }
        return null;
    }

    public Account login(long id,String password) throws BusinessException{
        Account account = getAccount(id);
        if(account.getPassword().equals(password)){
            return account;
        }
        throw new LoginException(BusinessException.LOGIN_ERROR_CODE,"找不到该用户或者密码错误！");
    }

    public Account deposit(long id,double deposit){
        Account account = getAccount(id);
        try {
            account.deposit(deposit);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return account;
    }

    public Account withdraw(long id,double withdraw){
        Account account = getAccount(id);
        try {
            account.withdraw(withdraw);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return account;
    }

    public Account setCeiling(long id,int ceiling){
        Account account = getAccount(id);
        if(account instanceof CreditAccount){
            if(ceiling>0){
                ((CreditAccount) account).setCeiling(ceiling);
            }
        }
        return account;
    }

    public double calcTotalBalance(){
        double total=0;
        for(int i=0;i< accounts.size();i++){
            total+=accounts.get(i).getBalance();
        }
        return total;
    }

    public double calcTotalCeiling(){
        double total=0;
        for(int i=0;i< accounts.size();i++){
            if(accounts.get(i) instanceof CreditAccount){
                total+=((CreditAccount) accounts.get(i)).getCeiling();
            }
        }
        return total;
    }

    public Account requestLoan(long id,double loan) throws BusinessException{
        Account account = getAccount(id);
        if(account instanceof Loan){
            ((Loan) account).requestLoan(loan);
            return account;
        }
        throw new NotLoanAccountException(BusinessException.NOT_LOAN_ACCOUNT_ERROR_CODE,"该账户不是可贷款用户");
    }

    public Account payLoan(long id,double loan) throws BusinessException{
        Account account = getAccount(id);
        if(account instanceof Loan){
            ((Loan) account).payLoan(loan);
            return account;
        }
        throw new NotLoanAccountException(BusinessException.NOT_LOAN_ACCOUNT_ERROR_CODE,"该账户不是可贷款用户");
    }

    public boolean saveAccount(Account account) throws IOException {
        if(account instanceof LoanCreditAccount){
            bw.write("1\n");
        }else if(account instanceof LoanSavingAccount){
            bw.write("2\n");
        }else if(account instanceof CreditAccount){
            bw.write("3\n");
        }else if(account instanceof SavingAccount){
            bw.write("4\n");
        }
        bw.write(account.getId()+"\n");
        bw.write(account.getPersonId()+"\n");
        bw.write(account.getName()+"\n");
        bw.write(account.getPassword()+"\n");
        bw.write(account.getEmail()+"\n");
        bw.write(account.getBalance()+"\n");
        if(account instanceof CreditAccount){
            bw.write(((CreditAccount) account).getCeiling()+"\n");
        }
        bw.close();
        return true;
    }

    public boolean readAccount() throws IOException {
        while(br.ready()){
            String temp = br.readLine();
            Account account=null;
            if(temp.equals("1")){
                account = new LoanCreditAccount();
            }else if(temp.equals("2")){
                account = new LoanSavingAccount();
            }else if(temp.equals("3")){
                account = new CreditAccount();
            }else if(temp.equals("4")){
                account = new SavingAccount();
            }
            if(account!=null){
                account.setId(Long.parseLong(br.readLine()));
                account.setPersonId(br.readLine());
                account.setName(br.readLine());
                account.setPassword(br.readLine());
                account.setEmail(br.readLine());
                account.setBalance(Double.parseDouble(br.readLine()));
                if(account instanceof CreditAccount){
                    ((CreditAccount) account).setCeiling(Double.parseDouble(br.readLine()));
                }
                accounts.add(account);
            }
        }
        this.count=accounts.size();
        return true;
    }

    public void printAllAccounts(){
        accounts.stream().forEach(System.out::println);
    }

    public void printRank(){
        Map<String,List<Account>> accountmap= new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<Account> accountList = accountmap.get(accounts.get(i).getPersonId());
            if(accountList==null){
                List<Account> singleAccounts=new ArrayList<>();
                singleAccounts.add(accounts.get(i));
                accountmap.put(accounts.get(i).getPersonId(),singleAccounts);
            }else {
                accountList.add(accounts.get(i));
            }
        }
        List<Account> singAccounts=new ArrayList<>();
        for(String personId:accountmap.keySet()){
            List<Account> acs = accountmap.get(personId);
            double total=0;
            for (int i = 0; i < acs.size(); i++) {
                total+=acs.get(i).getBalance();
            }
            Account account = new CreditAccount();
            account.setBalance(total);
            account.setPersonId(personId);
            singAccounts.add(account);
        }
        Account[] singleAccountArrays = singAccounts.toArray(new Account[singAccounts.size()]);
        Arrays.sort(singleAccountArrays);
        for (int i = 0; i < singleAccountArrays.length; i++) {
            System.out.println("personId:"+singleAccountArrays[i].getPersonId()+"  账户总额: "+singleAccountArrays[i].getBalance());
        }
    }

    public static void main(String[] args) {
        try {
            Bank bank=Bank.getInstance();
            bank.addAccount();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }
    }
}
