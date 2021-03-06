package com.imie.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.persistence.Transient;

/**
 * Classe représentant un média en base.
 */
@NamedQueries({
	@NamedQuery(name = "Media.findAll", query = "SELECT m FROM Media m"),
	@NamedQuery(name = "Media.getDerniersPublies", query = "SELECT m FROM Media m ORDER BY m.datecreation DESC")
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
	@Column
	private String titre;

	/** Description. */
	@Column
	private String description;
	
	/** Date de création. */
	@Column
	private Timestamp datecreation;

	/** Indicateur spécifiant si le média est publique ou privé. */
	@Column
	private Boolean publique;
	
	/**
	 * La liste des mots clés séparés par des point-virgules.
	 */
	@Transient
	private transient String motsClefs;

	/* *************************************** */
	/* R E L A T I O N S */
	/* *************************************** */
	
	/** Publieur. */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "publieur")
	private Utilisateur publieur;

	/** Thème principal. */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "theme")
	private Tag mainTheme;

	/** The liste tags. */
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(
			name="associationtag",
			joinColumns = @JoinColumn(name="media"),
			inverseJoinColumns = @JoinColumn(name="tag")
	)
	private List<Tag> listeTags;

	/* *************************************** */
	/* M É T H O D E S */
	/* *************************************** */
	
	/** Constructeur par défaut. */
	public Media() {
		super();
		listeTags = new ArrayList<Tag>();
	}
	
	@Override
	public String toString() {
		return "Media [id=" + id + ", titre=" + titre + ", description="
				+ description + ", publique=" + publique + ", publieur=" + publieur.getNom() + " " + publieur.getPrenom()
				+ ", mainTheme=" + mainTheme + ", listeTags=" + listeTags + "]";
	}

	/**
	 * Ajoute un tag à la liste de tags et met le média en référence du tag.
	 * @param tag Le tag à ajouter.
	 */
	public void addTag(Tag tag) {
		listeTags.add(tag);
		tag.addMedia(this);
	}
	
	public boolean isFichier() {
		return this instanceof Fichier;
	}
	
	public boolean isPhoto() {
		return this instanceof Photo;
	}

	public boolean isMusique() {
		return this instanceof Musique;
	}
	
	public boolean isVideo() {
		return this instanceof Video;
	}
	
	public boolean isEpisode() {
		return this instanceof Episode;
	}
	
	public boolean isFilm() {
		return this instanceof Film;
	}
	
	public boolean isGalerie() {
		return this instanceof Galerie;
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(Timestamp datecreation) {
		this.datecreation = datecreation;
	}

	public Boolean isPublique() {
		return publique;
	}
	
	// Utile pour les JSP
	public Boolean getPublique() {
		return publique;
	}

	public void setPublique(Boolean publique) {
		this.publique = publique;
	}

	public Utilisateur getPublieur() {
		return publieur;
	}

	public void setPublieur(Utilisateur publieur) {
		this.publieur = publieur;
		publieur.getMedias().add(this);
	}

	public Tag getMainTheme() {
		return mainTheme;
	}

	public void setMainTheme(Tag mainTheme) {
		this.mainTheme = mainTheme;
	}

	public List<Tag> getListeTags() {
		return listeTags;
	}

	public void setListeTags(List<Tag> listeTags) {
		this.listeTags = listeTags;
	}

	public Integer getId() {
		return id;
	}

	public String getMotsClefs() {
		return motsClefs;
	}

	public void setMotsClefs(String motsClefs) {
		this.motsClefs = motsClefs;
	}
}