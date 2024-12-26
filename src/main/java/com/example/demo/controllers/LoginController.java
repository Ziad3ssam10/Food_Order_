package com.example.demo.controllers;

import com.example.demo.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Button registerpage_btn;
    public Button loginpage_btn;
    public TextField email_fild;
    public PasswordField pass_fild;
    public Button login_btn;
    public Button customertype_btn;
    public Button deliverytype_btn;
    public Button admintype_btn;
    public Label customertype_lbl;
    public Label deliverytype_lbl;
    public Label admintype_lbl;
    public Label Error_lbl;


    private boolean cust=true;
    private boolean delivery=false;
    private boolean admin=false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customertype_btn.setOnAction(event -> oncustomertype());
        deliverytype_btn.setOnAction(event -> ondeliverytype());
        admintype_btn.setOnAction(event -> onadmintype());
        registerpage_btn.setOnAction(event -> gotoregister());
        login_btn.setOnAction(event -> onlogin());
    }

    private void onlogin(){
        if (cust) {
            boolean found2=false;
            for (int i=0;i<Main.capitals.size();i++) {
                boolean found = false;
                for (int j=0;j<Main.capitals.get(i).getCustomers().size();j++) {
                    if (Main.capitals.get(i).getCustomers().get(j).get_Email().equals(email_fild.getText().toLowerCase()) && Main.capitals.get(i).getCustomers().get(j).get_pass().equals(pass_fild.getText())){
                        Main.governoridx=i;
                        Main.customeridx=j;
                        Model.getInstance().getViewFactory().ShowfirstcusWindow();
                        found = true;
                        break;
                    }
                }
                if(found){
                    found2=true;
                    break;
                }
            }
            if(!found2){
                Error_lbl.setVisible(true);
            }
        }
        else if (delivery) {
            boolean found2=false;
            for (int i=0;i<Main.capitals.size();i++) {
                boolean found = false;
                for (int j=0;j<Main.capitals.get(i).getArea_list().size();j++) {
                    boolean found3=false;
                    for(int k=0;k<Main.capitals.get(i).getArea_list().get(j).getDelievryStaffs().size();k++) {
                        if (Main.capitals.get(i).getArea_list().get(j).getDelievryStaffs().get(k).get_Email().equals(email_fild.getText().toLowerCase()) && Main.capitals.get(i).getArea_list().get(j).getDelievryStaffs().get(k).get_pass().equals(pass_fild.getText())) {
                            Main.governoridx=i;
                            Main.area_del_idx=j;
                            Main.deliveryidx=k;
                            Model.getInstance().getViewFactory().Show_delivery_ordersWindow();
                            found3 = true;
                            break;
                        }
                    }
                    if(found3){
                        found=true;
                        break;
                    }
                }
                if(found){
                    found2=true;
                    break;
                }
            }
            if(!found2){
                Error_lbl.setVisible(true);
            }
        }
        else if (admin) {
            boolean found=false;
            for (int i=0;i<Main.moderators.size();i++){
                if (Main.moderators.get(i).get_Email().equals(email_fild.getText().toLowerCase()) && Main.moderators.get(i).get_pass().equals(pass_fild.getText())){
                    Main.adminidx=i;
                    Model.getInstance().getViewFactory().Show_firstpageadmin();
                    found = true;
                    break;
                }
            }
            if(!found){
                Error_lbl.setVisible(true);
            }
        }
        else{
            Error_lbl.setVisible(true);
        }
    }
    private void gotoregister(){
        Model.getInstance().getViewFactory().ShowRegisterWindow();
    }

    private void oncustomertype(){
        cust=true;
        delivery=false;
        admin=false;
        customertype_lbl.setTextFill(Color.rgb(155, 254, 3, 1));
        customertype_btn.setStyle("-fx-background-color: #9BFE03;\n" +
                "    -fx-background-radius: 50px;");
        deliverytype_lbl.setTextFill(Color.rgb(233, 250, 200, 1));
        deliverytype_btn.setStyle(" -fx-background-color: #3B3F34;\n" +
                "    -fx-background-radius: 50px;\n" +
                "    -fx-border-color: #E9FAC8;\n" +
                "    -fx-border-radius: 26px;\n" +
                "    -fx-border-width: 3px;");
        admintype_lbl.setTextFill(Color.rgb(233, 250, 200, 1));
        admintype_btn.setStyle(" -fx-background-color: #3B3F34;\n" +
                "    -fx-background-radius: 50px;\n" +
                "    -fx-border-color: #E9FAC8;\n" +
                "    -fx-border-radius: 26px;\n" +
                "    -fx-border-width: 3px;");
    }
    private void ondeliverytype(){
        cust=false;
        delivery=true;
        admin=false;
        deliverytype_lbl.setTextFill(Color.rgb(155, 254, 3, 1));
        deliverytype_btn.setStyle("-fx-background-color: #9BFE03;\n" +
                "    -fx-background-radius: 50px;" + " -fx-border-color:#9BFE03 ");
        customertype_lbl.setTextFill(Color.rgb(233, 250, 200, 1));
        customertype_btn.setStyle(" -fx-background-color: #3B3F34;\n" +
                "    -fx-background-radius: 50px;\n" +
                "    -fx-border-color: #E9FAC8;\n" +
                "    -fx-border-radius: 26px;\n" +
                "    -fx-border-width: 3px;");

        admintype_lbl.setTextFill(Color.rgb(233, 250, 200, 1));
        admintype_btn.setStyle(" -fx-background-color: #3B3F34;\n" +
                "    -fx-background-radius: 50px;\n" +
                "    -fx-border-color: #E9FAC8;\n" +
                "    -fx-border-radius: 26px;\n" +
                "    -fx-border-width: 3px;");
    }
    private void onadmintype(){
        cust=false;
        delivery=false;
        admin=true;
        admintype_lbl.setTextFill(Color.rgb(155, 254, 3, 1));
        admintype_btn.setStyle("-fx-background-color: #9BFE03;\n" +
                "    -fx-background-radius: 50px;"+" -fx-border-color:#9BFE03 ");
        deliverytype_lbl.setTextFill(Color.rgb(233, 250, 200, 1));
        deliverytype_btn.setStyle(" -fx-background-color: #3B3F34;\n" +
                "    -fx-background-radius: 50px;\n" +
                "    -fx-border-color: #E9FAC8;\n" +
                "    -fx-border-radius: 26px;\n" +
                "    -fx-border-width: 3px;");
        customertype_lbl.setTextFill(Color.rgb(233, 250, 200, 1));
        customertype_btn.setStyle(" -fx-background-color: #3B3F34;\n" +
                "    -fx-background-radius: 50px;\n" +
                "    -fx-border-color: #E9FAC8;\n" +
                "    -fx-border-radius: 26px;\n" +
                "    -fx-border-width: 3px;");
    }
}
