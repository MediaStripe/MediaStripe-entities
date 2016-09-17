package com.imie.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe représentant un tag en base.
 */
@Entity
@Table(name = "tag")
@NamedQueries({
	@NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t"),
	@NamedQuery(name = "Tag.findByLibelle", query = "SELECT t FROM Tag t WHERE t.libelle = :libelle")
})
public class Tag implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Identifiant technique. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	/** Libelle. */
	@Column(length = 150, unique = true, nullable = false)
	private String libelle;

	/** Liste des médias associés au tag. */
	@OneToMany(mappedBy = "mainTheme", fetch = FetchType.LAZY)
	private List<Media> mediasAThemePrincipal;

	@ManyToMany(mappedBy = "listeTags")
	private List<Media> mediasAssocies;
	
	/** Constructeur par défaut. */
	public Tag() {
		super();
		mediasAThemePrincipal = new ArrayList<Media>();
		mediasAssocies = new ArrayList<Media>();
	}

	/**
	 * Initialise un nouveau tag à partir du libellé passé en paramètre.
	 * 
	 * @param libelle
	 *            Le libellé du tag.
	 */
	public Tag(String libelle) {
		this();
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		final StringBuilder bld = new StringBuilder();
		bld.append("Tag [id=" + id + ", libelle=" + libelle);
		return bld.toString();
	}

	/**
	 * Adds the media.
	 *
	 * @param media
	 *            the media
	 * @return the media
	 */
	public void addMedia(Media media) {
		mediasAssocies.add(media);
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
		return mediasAThemePrincipal;
	}

	/**
	 * Sets the medias.
	 *
	 * @param medias
	 *            the new medias
	 */
	public void setMedias(List<Media> medias) {
		this.mediasAThemePrincipal = medias;
	}

	public List<Media> getMediasAssocies() {
		return mediasAssocies;
	}

	public void setMediasAssocies(List<Media> mediasAssocies) {
		this.mediasAssocies = mediasAssocies;
	}

}