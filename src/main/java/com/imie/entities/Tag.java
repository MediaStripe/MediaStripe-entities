package com.imie.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe représentant un tag en base.
 */
@Entity
@Table(name = "tag")
@NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t")
public class Tag implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Identifiant technique. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	/** Libelle. */
	@Column(length = 150)
	private String libelle;

	/** Liste des médias associés au tag. */
	@OneToMany(mappedBy = "mainTheme", fetch = FetchType.EAGER)
	private List<Media> medias;

	/** Constructeur par défaut. */
	public Tag() {
		super();
		medias = new ArrayList<Media>();
	}
	
	@Override
	public String toString() {
		final StringBuilder bld = new StringBuilder();
		bld.append("Tag [id=" + id + ", libelle=" + libelle + ", medias=");
		bld.append(Arrays.toString(medias.toArray())).append("]");
		return bld.toString();
	}

	/**
	 * Adds the media.
	 *
	 * @param media
	 *            the media
	 * @return the media
	 */
	public Media addMedia(Media media) {
		getMedias().add(media);
		media.setMainTheme(this);
		return media;
	}

	/**
	 * Removes the media.
	 *
	 * @param media
	 *            the media
	 * @return the media
	 */
	public Media removeMedia(Media media) {
		getMedias().remove(media);
		media.setMainTheme(null);

		return media;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the libelle.
	 *
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Sets the libelle.
	 *
	 * @param libelle
	 *            the new libelle
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Gets the medias.
	 *
	 * @return the medias
	 */
	public List<Media> getMedias() {
		return medias;
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