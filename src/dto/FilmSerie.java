package dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



public class FilmSerie {
	
	private int id;
	private String name;
	private String poster;
	private LocalDateTime ddc;
	private LocalDateTime ddm;
	private LocalDate dds;
	private int typeId;
	private String typeName;
	private String synopsis;
	private int saisons;
	private int episodes;
	private int intervenant;
	private int intervenantRole;
	private List<Intervenant> intervenants;
	private List<String> genres;
	
	public FilmSerie() {
		
	}
	
	public List<Intervenant> getIntervenants() {
		return intervenants;
	}

	public void setIntervenants(List<Intervenant> intervenants) {
		this.intervenants = intervenants;
	}
	
	public void addIntervenant(Intervenant intervenant) {
		this.intervenants.add(intervenant);
	}

	
	
	public FilmSerie(
			String name, 
			String poster, 
			LocalDateTime ddc, 
			LocalDate dds, 
			int typeId, 
			String typeName, 
			String synopsis, 
			int saisons, 
			int episodes,  
			List<Intervenant> intervenants, 
			List<String> genres) {
		
		this.name = name;
		this.ddc = ddc;
		this.dds = dds;
		this.typeId = typeId;
		this.synopsis = synopsis;
		this.typeName = typeName;
		this.saisons = saisons;
		this.episodes = episodes;
		this.intervenants = intervenants;
		this.genres = genres;
		this.poster = poster;
		
	}
	
	public FilmSerie(
			int id,
			String name, 
			String poster, 
			LocalDateTime ddc, 
			LocalDate dds, 
			int typeId, 
			String typeName, 
			String synopsis, 
			int saisons, 
			int episodes,  
			List<Intervenant> intervenants, 
			List<String> genres) {
		
		this.id = id;
		this.name = name;
		this.ddc = ddc;
		this.dds = dds;
		this.typeId = typeId;
		this.synopsis = synopsis;
		this.typeName = typeName;
		this.saisons = saisons;
		this.episodes = episodes;
		this.intervenants = intervenants;
		this.genres = genres;
		this.poster = poster;
		
	}

	
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public LocalDateTime getDdc() {
		return ddc;
	}

	public void setDdc(LocalDateTime ddc) {
		this.ddc = ddc;
	}

	public LocalDateTime getDdm() {
		return ddm;
	}

	public void setDdm(LocalDateTime ddm) {
		this.ddm = ddm;
	}

	
	public LocalDate getDds() {
		return dds;
	}

	public void setDds(LocalDate dds) {
		this.dds = dds;
	}


	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public int getSaisons() {
		return saisons;
	}

	public void setSaisons(int saisons) {
		this.saisons = saisons;
	}

	public int getEpisodes() {
		return episodes;
	}

	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}
	

	public int getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(int intervenant) {
		this.intervenant = intervenant;
	}

	@Override
	public String toString() {
		return "FilmSerie [id=" + id + ", name=" + name + ", poster=" + poster + ", ddc=" + ddc + ", ddm=" + ddm
				+ ", dds=" + dds + ", typeId=" + typeId + ", typeName=" + typeName + ", synopsis=" + synopsis
				+ ", saisons=" + saisons + ", episodes=" + episodes + ", intervenants=" + intervenants + "]";
	}

	public int getIntervenantRole() {
		return intervenantRole;
	}

	public void setIntervenantRole(int intervenantRole) {
		this.intervenantRole = intervenantRole;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public void addGenre(String genre) {
		this.genres.add(genre);
	}
	

}
