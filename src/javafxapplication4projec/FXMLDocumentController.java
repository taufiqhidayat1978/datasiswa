/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4projec;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import javafx.scene.control.TextField;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author opic
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button but1;
    @FXML
    private TableView<DatasiswaBean> tabel_siswa;
    @FXML
    private TableColumn<DatasiswaBean, String> colNis;
    @FXML
    private TableColumn<DatasiswaBean, String> colNama;
    @FXML
    private TableColumn<DatasiswaBean, String> colTempat;
    @FXML
    private TableColumn<DatasiswaBean, String> colTanggal;
    
    EntityManagerFactory emf;
    DatasiswaJpaController jpa;
    @FXML
    private TextField tf2;
    @FXML
    private TextField tf3;
    @FXML
    private TextField tf4;
    @FXML
    private TextField tf1;
    @FXML
    private Button but2;
    @FXML
    private Button but3;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        emf = Persistence.createEntityManagerFactory("JavaFXApplication4projecPU");
        jpa = new DatasiswaJpaController(emf);

        tampilTabel();
    }    

    private void tampilTabel() {
    emf = Persistence.createEntityManagerFactory("JavaFXApplication4projecPU");
        jpa = new DatasiswaJpaController(emf);

        EntityManager em;
        em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Datasiswa> list = em.createNamedQuery("Datasiswa.findAll", Datasiswa.class).getResultList();
        for (Datasiswa sis : list) {
            tabel_siswa.getItems().add(new DatasiswaBean(sis.getNis(), sis.getNama(), sis.getTempatLahir(),sis.getTanggalLahir()));

//            System.out.println("ID: "+sis.getId());
//            System.out.println("Nama: "+sis.getNama());
//            System.out.println("Kelas: "+sis.getKelas());
//            tabel_siswa.setItems(list);
//            tabel_siswa.setItems(list);
            colNis.setCellValueFactory(new PropertyValueFactory<>("nis"));
            colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
            colTempat.setCellValueFactory(new PropertyValueFactory<>("tempatLahir"));
            colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggalLahir"));
        }

    }

    @FXML
    private void simpanData(ActionEvent event) {
     Datasiswa a = new Datasiswa();
        a.setNis(Integer.parseInt(tf1.getText()));
        a.setNama(tf2.getText());
        a.setTempatLahir(tf3.getText());
        //a.setTanggalLahir(tf4.getText());

        try {
            jpa.create(a);
            tabel_siswa.getItems().clear();
            tampilTabel();

        } catch (Exception ex) {
            System.out.println("ex = " + ex);
        }
    }
    
}
