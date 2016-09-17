package com.imie.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe représentant une playlist de médias en base.
 */
@NamedQuery(name = "Playlist.findAll", query = "SELECT p FROM Playlist p")
@Entity
@Table(name = "playlist")
public class Playlist extends Media implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8525647155781355496L;

	/** The nom. */
	@Column(length = 255)
	private String nom;

	/** The medias. */
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinTable(
			name = "contenuplaylist", 
			joinColumns = @JoinColumn(name = "idplaylist", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "idmedia", referencedColumnName = "id")
	)
	private List<Media> medias;

	/** Constructeur par défaut. */
	public Playlist() {
		super();
	}

	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Sets the nom.
	 *
	 * @param nom
	 *            the new nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Gets the medias.
	 *
	 * @return the medias
	 */
	public List<Media> getMedias() {
		return this.medias;
	}

	/**
	 * Sets the medias.
	 *
	 * @param medias
	 *            the new medias
	 */
	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

}