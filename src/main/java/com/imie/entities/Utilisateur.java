package com.imie.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe représentant un utilisateur en base.
 */
@Entity
@Table(name = "utilisateur")
@NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Identifiant technique en base. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;
	
	/** Nom. */
	@Column(length = 100)
	private String nom;

	/** Prénom. */
	@Column(length = 100)
	private String prenom;
	
	/** Adresse email. */
	@Column(length = 150)
	private String mail;
	
	/** Date d'inscription. */
	@Temporal(TemporalType.DATE)
	@Column
	private Date dateinscription;
	
	/** Date de la dernière connexion. (comportant l'heure exacte) */
	@Column
	private Timestamp derniereconnexion;
	
	/** Mot de passe. */
	@Column(length = 255)
	private String motdepasse;
	
	/**
	 * Indicateur spécifiant si l'utilisateur possède des droits
	 * d'administrateur.
	 */
	private Boolean administrateur;

	/** Liste des médias qu'a publié l'utilisateur. */
	@OneToMany(mappedBy = "publieur", fetch = FetchType.EAGER)
	private List<Media> medias;

	/**
	 * Liste des sales qu'a créé l'utilisateur (uniquement s'il est
	 * administrateur).
	 */
	@OneToMany(mappedBy = "createur", fetch = FetchType.EAGER)
	private List<Salle> salles;

	/** Liste de contacts. */
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="contact",
			joinColumns = @JoinColumn(name="utilisateur1", 
					referencedColumnName="id"),
			inverseJoinColumns = @JoinColumn(name="utilisateur2", 
					referencedColumnName = "id")
	)
	private List<Utilisateur> contacts;

	/** Liste des utilisateurs publieurs que ce dernier suit. */
	@ManyToMany(fetch=FetchType.EAGER)
	// XXX : joinColumn et inverseJoinColumn à inverser ?
	@JoinTable(
			name="abonnement",
			joinColumns = @JoinColumn(name="publieur", 
					referencedColumnName="id"),
			inverseJoinColumns = @JoinColumn(name="abonne", 
					referencedColumnName = "id")
	)
	private List<Utilisateur> publishers;

	/** Constructeur par défaut. */
	public Utilisateur() {
		super();
		medias = new ArrayList<Media>();
		salles = new ArrayList<Salle>();
		contacts = new ArrayList<Utilisateur>();
		publishers = new ArrayList<Utilisateur>();
	}

	@Override
	public String toString() {
		final StringBuilder bld = new StringBuilder();
		
		bld.append("Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom
				+ ", mail=" + mail + ", dateinscription=" + dateinscription
				+ ", derniereconnexion=" + derniereconnexion + ", motdepasse="
				+ motdepasse + ", administrateur=" + administrateur
				+ ", medias=");
		
		bld.append(Arrays.toString(medias.toArray())).append(", salles=");
		bld.append(Arrays.toString(salles.toArray())).append(", contacts=");
		bld.append(Arrays.toString(contacts.toArray())).append(", publishers=");
		bld.append(Arrays.toString(publishers.toArray())).append("]");
		
		return bld.toString();
	}

	/**
	 * Adds the salle.
	 *
	 * @param salle
	 *            the salle
	 * @return the salle
	 */
	public Salle addSalle(Salle salle) {
		getSalles().add(salle);
		salle.setUtilisateur(this);

		return salle;
	}

	/**
	 * Removes the salle.
	 *
	 * @param salle
	 *            the salle
	 * @return the salle
	 */
	public Salle removeSalle(Salle salle) {
		getSalles().remove(salle);
		salle.setUtilisateur(null);

		return salle;
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
	 * Gets the administrateur.
	 *
	 * @return the administrateur
	 */
	public Boolean getAdministrateur() {
		return administrateur;
	}

	/**
	 * Sets the administrateur.
	 *
	 * @param administrateur
	 *            the new administrateur
	 */
	public void setAdministrateur(Boolean administrateur) {
		this.administrateur = administrateur;
	}

	/**
	 * Gets the dateinscription.
	 *
	 * @return the dateinscription
	 */
	public Date getDateinscription() {
		return dateinscription;
	}

	/**
	 * Sets the dateinscription.
	 *
	 * @param dateinscription
	 *            the new dateinscription
	 */
	public void setDateinscription(Date dateinscription) {
		this.dateinscription = dateinscription;
	}

	/**
	 * Gets the derniereconnexion.
	 *
	 * @return the derniereconnexion
	 */
	public Timestamp getDerniereconnexion() {
		return derniereconnexion;
	}

	/**
	 * Sets the derniereconnexion.
	 *
	 * @param derniereconnexion
	 *            the new derniereconnexion
	 */
	public void setDerniereconnexion(Timestamp derniereconnexion) {
		this.derniereconnexion = derniereconnexion;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail
	 *            the new mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Gets the motdepasse.
	 *
	 * @return the motdepasse
	 */
	public String getMotdepasse() {
		return motdepasse;
	}

	/**
	 * Sets the motdepasse.
	 *
	 * @param motdepasse
	 *            the new motdepasse
	 */
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	public String getNom() {
		return nom;
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
	 * Gets the prenom.
	 *
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Sets the prenom.
	 *
	 * @param prenom
	 *            the new prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

	/**
	 * Gets the salles.
	 *
	 * @return the salles
	 */
	public List<Salle> getSalles() {
		return salles;
	}

	/**
	 * Sets the salles.
	 *
	 * @param salles
	 *            the new salles
	 */
	public void setSalles(List<Salle> salles) {
		this.salles = salles;
	}

	/**
	 * Gets the contacts.
	 *
	 * @return the contacts
	 */
	public List<Utilisateur> getContacts() {
		return contacts;
	}

	/**
	 * Sets the contacts.
	 *
	 * @param contacts
	 *            the new contacts
	 */
	public void setContacts(List<Utilisateur> contacts) {
		this.contacts = contacts;
	}

	/**
	 * Gets the publishers.
	 *
	 * @return the publishers
	 */
	public List<Utilisateur> getPublishers() {
		return publishers;
	}

	/**
	 * Sets the publishers.
	 *
	 * @param publishers
	 *            the new publishers
	 */
	public void setPublishers(List<Utilisateur> publishers) {
		this.publishers = publishers;
	}

}