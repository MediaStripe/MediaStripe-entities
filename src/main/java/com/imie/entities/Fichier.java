package com.imie.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe représentant un fichier en base.
 */
@NamedQueries({
	@NamedQuery(name = "Fichier.findAll", query = "SELECT f FROM Fichier f")
})
@Entity
@Table(name = "fichier")
public class Fichier extends Media implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8616598340550463983L;
	
	/** Chemin du fichier sur le serveur. */
	@Column(length = 255)
	private String cheminfichier;
	
	/**
	 * La liste des m
	 */
	private transient String motsClefs;

	/** Constructeur par défaut. */
	public Fichier() {
		super();
	}

	/**
	 * Gets the cheminfichier.
	 *
	 * @return the cheminfichier
	 */
	public String getCheminfichier() {
		return this.cheminfichier;
	}

	/**
	 * Sets the cheminfichier.
	 *
	 * @param cheminfichier
	 *            the new cheminfichier
	 */
	public void setCheminfichier(String cheminfichier) {
		this.cheminfichier = cheminfichier;
	}

	/**
	 * Gets the mots clefs.
	 *
	 * @return the mots clefs
	 */
	public String getMotsClefs() {
		return motsClefs;
	}

	/**
	 * Sets the mots clefs.
	 *
	 * @param motsClefs the new mots clefs
	 */
	public void setMotsClefs(String motsClefs) {
		this.motsClefs = motsClefs;
	}
}