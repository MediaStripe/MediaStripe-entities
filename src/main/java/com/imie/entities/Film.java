package com.imie.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe représentant un film en base.
 */
@NamedQueries({ @NamedQuery(name = "Film.findAll", query = "SELECT f FROM Film f") })
@Entity
@Table(name = "film")
public class Film extends Video implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The realisateur. */
	@Column(length = 255)
	private String realisateur;

	/**
	 * Constructeur par défaut.
	 */
	public Film() {
		super();
	}

	/**
	 * Gets the realisateur.
	 *
	 * @return the realisateur
	 */
	public String getRealisateur() {
		return this.realisateur;
	}

	/**
	 * Sets the realisateur.
	 *
	 * @param realisateur
	 *            the new realisateur
	 */
	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}
}