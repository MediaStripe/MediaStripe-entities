package com.imie.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe représentant un média en base.
 */
@NamedQueries({
	@NamedQuery(name = "Media.findAll", query = "SELECT m FROM Media m")
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "media")
public class Media implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7792605811892573819L;

	/** Identifiant technique en base. */
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** Titre. */
	@Column(length = 150)
	private String titre;

	/** Description. */
	@Column(length = 255)
	private String description;
	
	/** Date de création. */
	@Column
	private Timestamp datecreation;

	/** Indicateur spécifiant si le média est publique ou privé. */
	@Column
	private Boolean publique;

	/** Publieur. */
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "publieur")
	private Utilisateur publieur;

	/** Thème principal. */
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "theme")
	private Tag mainTheme;

	/** The liste tags. */
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name="associationtag",
			joinColumns = @JoinColumn(name="media", 
									referencedColumnName="id"),
			inverseJoinColumns = @JoinColumn(name="tag", 
									referencedColumnName = "id")
	)
	private List<Tag> listeTags;

	/** Constructeur par défaut. */
	public Media() {
		super();
		listeTags = new ArrayList<Tag>();
	}
	
	@Override
	public String toString() {
		return "Media [id=" + id + ", titre=" + titre + ", description="
				+ description + ", datecreation=" + datecreation
				+ ", publique=" + publique + ", publieur=" + publieur
				+ ", mainTheme=" + mainTheme + ", listeTags=" + listeTags + "]";
	}

	/**
	 * Ajoute un tag à la liste de tags et met le média en référence du tag.
	 * @param tag Le tag à ajouter.
	 */
	public void addTag(Tag tag) {
		listeTags.add(tag);
		tag.getMedias().add(this);
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
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the titre.
	 *
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Sets the titre.
	 *
	 * @param titre the new titre
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the datecreation.
	 *
	 * @return the datecreation
	 */
	public Timestamp getDatecreation() {
		return datecreation;
	}

	/**
	 * Sets the datecreation.
	 *
	 * @param datecreation the new datecreation
	 */
	public void setDatecreation(Timestamp datecreation) {
		this.datecreation = datecreation;
	}

	/**
	 * Gets the publique.
	 *
	 * @return the publique
	 */
	public Boolean getPublique() {
		return publique;
	}

	/**
	 * Sets the publique.
	 *
	 * @param publique the new publique
	 */
	public void setPublique(Boolean publique) {
		this.publique = publique;
	}

	/**
	 * Gets the publieur.
	 *
	 * @return the publieur
	 */
	public Utilisateur getPublieur() {
		return publieur;
	}

	/**
	 * Sets the publieur.
	 *
	 * @param publieur the new publieur
	 */
	public void setPublieur(Utilisateur publieur) {
		this.publieur = publieur;
	}

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public Tag getMainTheme() {
		return mainTheme;
	}

	/**
	 * Sets the tag.
	 *
	 * @param tag the new tag
	 */
	public void setMainTheme(Tag tag) {
		this.mainTheme = tag;
	}

	/**
	 * Gets the liste tags.
	 *
	 * @return the liste tags
	 */
	public List<Tag> getListeTags() {
		return listeTags;
	}

	/**
	 * Sets the liste tags.
	 *
	 * @param listeTags the new liste tags
	 */
	public void setListeTags(List<Tag> listeTags) {
		this.listeTags = listeTags;
	}
}