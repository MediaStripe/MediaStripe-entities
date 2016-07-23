package com.imie.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe représentant un épisode de série en base.
 */
@NamedQueries({ @NamedQuery(name = "Episode.findAll", query = "SELECT e FROM Episode e") })
@Entity
@Table(name = "episode")
public class Episode extends Video implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The numero. */
	private Integer numero;

	/** The serie. */
	@Column(length = 255)
	private String serie;

	/**
	 * Constructeur par défaut.
	 */
	public Episode() {
		super();
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public Integer getNumero() {
		return this.numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * Gets the serie.
	 *
	 * @return the serie
	 */
	public String getSerie() {
		return this.serie;
	}

	/**
	 * Sets the serie.
	 *
	 * @param serie
	 *            the new serie
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}
}