package com.imie.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe représentant une galerie de photos en base.
 */
@NamedQuery(name = "Galerie.findAll", query = "SELECT g FROM Galerie g")
@Entity
@Table(name = "galerie")
public class Galerie extends Playlist implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7542617192928430347L;

	/** Liste des photos. */
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinTable(
			name="contenugalerie",
			joinColumns = @JoinColumn(name="galerie", referencedColumnName="id"),
			inverseJoinColumns = @JoinColumn(name="photo", referencedColumnName = "id")
	)
	private List<Photo> photos;

	/**
	 * Constructeur par défaut.
	 */
	public Galerie() {
		super();
	}

	/**
	 * Gets the photos.
	 *
	 * @return the photos
	 */
	public List<Photo> getPhotos() {
		return this.photos;
	}

	/**
	 * Sets the photos.
	 *
	 * @param photos
	 *            the new photos
	 */
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

}