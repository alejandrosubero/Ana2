package com.alejandro.ana.entity;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "proyecto")
public class Proyecto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2293887833717525692L;

	// @Id
	// @GeneratedValue
    // @Column(name = "id")
    // private Long id;

//	@Id
//	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//	@GenericGenerator(name = "native", strategy = "native")

	@Id
	@GeneratedValue(generator = "mat_id_generator")
	@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
	@Column(name = "id", updatable = true, nullable = false, length = 25)
	private Long id;


    @Column(name = "name")
	private String name;
    
    @Column(name = "mimetype")
	private String mimetype;
	
	@Lob
    @Column(name="bic")
    private byte[] bic;

	
	public Proyecto( ) {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public byte[] getPic() {
		return bic;
	}

	public void setPic(byte[] pic) {
		this.bic = pic;
	}

	
}
