package com.imie.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe représentant une salle en base.
 */
@NamedQuery(name = "Salle.findAll", query = "SELECT s FROM Salle s")
@Entity
@Table(name = "salle")
public class Salle implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Identifiant technique en base. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	/** Nom. */
	@Column(length = 150)
	private String nom;

	/** Créateur de la salle. */
	@ManyToOne
	@JoinColumn(name = "createur")
	private Utilisateur createur;

	/**
	 * Instantiates a new salle.
	 */
	public Salle() {
		super();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
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
	 * Gets the utilisateur.
	 *
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return this.createur;
	}

	/**
	 * Sets the utilisateur.
	 *
	 * @param utilisateur
	 *            the new utilisateur
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.createur = utilisateur;
	}

}