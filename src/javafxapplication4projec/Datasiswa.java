/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4projec;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author opic
 */
@Entity
@Table(name = "DATASISWA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datasiswa.findAll", query = "SELECT d FROM Datasiswa d")
    , @NamedQuery(name = "Datasiswa.findByNis", query = "SELECT d FROM Datasiswa d WHERE d.nis = :nis")
    , @NamedQuery(name = "Datasiswa.findByNama", query = "SELECT d FROM Datasiswa d WHERE d.nama = :nama")
    , @NamedQuery(name = "Datasiswa.findByTempatLahir", query = "SELECT d FROM Datasiswa d WHERE d.tempatLahir = :tempatLahir")
    , @NamedQuery(name = "Datasiswa.findByTanggalLahir", query = "SELECT d FROM Datasiswa d WHERE d.tanggalLahir = :tanggalLahir")})
public class Datasiswa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NIS")
    private Integer nis;
    @Column(name = "NAMA")
    private String nama;
    @Column(name = "TEMPAT_LAHIR")
    private String tempatLahir;
    @Column(name = "TANGGAL_LAHIR")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;

    public Datasiswa() {
    }

    public Datasiswa(Integer nis) {
        this.nis = nis;
    }

    public Integer getNis() {
        return nis;
    }

    public void setNis(Integer nis) {
        this.nis = nis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nis != null ? nis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datasiswa)) {
            return false;
        }
        Datasiswa other = (Datasiswa) object;
        if ((this.nis == null && other.nis != null) || (this.nis != null && !this.nis.equals(other.nis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javafxapplication4projec.Datasiswa[ nis=" + nis + " ]";
    }
    
}
