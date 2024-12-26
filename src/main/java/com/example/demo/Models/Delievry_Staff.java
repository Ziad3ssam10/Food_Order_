package com.example.demo.Models;

import com.example.demo.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Delievry_Staff extends User{
        private String area;
        private String governorate;
        private int age;
        private String Phone_number;
        private Boolean deliverystaff_status;
        private int idx_gov;

        private List<Order> orders = new ArrayList<>();


        public Delievry_Staff() {
            super();
            this.area = "";
            this.governorate = "";
            this.age = 0;
            this.Phone_number = "";
            this.deliverystaff_status = true;
            this.idx_gov = 0;
        }





        public int Register(String F_name, String L_name, String Email, String Password, String Phone_number, int age, String Area1, String Governorate1) {
            try {
                int check = super.Register(F_name.toLowerCase(), L_name.toLowerCase(), Email.toLowerCase(), Password);
                if (Phone_number.length() != 11 || Phone_number.charAt(0) != '0') {
                    check = -1;
                }
                this.age = age;
                this.Phone_number = Phone_number;
                this.area = Area1;
                this.governorate = Governorate1;
                this.idx_gov = Governorate.get_index(governorate);
                return check;
            }
            catch (NumberFormatException e) {
                return -1;
            }
        }


        public void Change_state_order(int id) {

            for(int i=0;i<this.orders.size();i++)
            {
                if(this.orders.get(i).getId()==id)
                {
                    this.orders.get(i).setStatus(1);
                }
            }

            this.deliverystaff_status = true;


            for (int k=0;k<Main.capitals.size();k++) {
                for (int i = 0; i < Main.capitals.get(k).getCustomers().size(); i++) {
                    for (int j = 0; j < Main.capitals.get(k).getCustomers().get(i).getOrders().size(); j++) {
                        if (Main.capitals.get(k).getCustomers().get(i).getOrders().get(j).getArea().equals(area) && !Main.capitals.get(k).getCustomers().get(i).getOrders().get(j).getAssigne_To_delivery() && Main.capitals.get(k).getCustomers().get(i).getOrders().get(j).getGovernorate().equals(governorate)) {
                            this.orders.add(Main.capitals.get(k).getCustomers().get(i).getOrders().get(j));
                            Main.capitals.get(k).getCustomers().get(i).getOrders().get(j).setStatus(-1);
                            Main.capitals.get(k).getCustomers().get(i).getOrders().get(j).setAssigne_To_delivery(true);
                            this.deliverystaff_status = false;
                            break;
                        }
                    }
                }
            }


        }


        public static int get_free_delivery(int govindex,int areaindex)
    {

        for(int i = 0; i< Main.capitals.get(govindex).getArea_list().get(areaindex).getDelievryStaffs().size(); i++)
        {
            if(Main.capitals.get(govindex).getArea_list().get(areaindex).getDelievryStaffs().get(i).deliverystaff_status)
            {
                return i;
            }
        }

        return -1;
    }





    @Override
    public String get_Email(){
        return this.Email;
    }

    @Override
    public String get_pass(){
        return this.Password;
    }

    public void set_deliverystaff_status(Boolean status){
            this.deliverystaff_status = status;
    }

    public List<Order> getOrders() {
        return orders;
    }

}
