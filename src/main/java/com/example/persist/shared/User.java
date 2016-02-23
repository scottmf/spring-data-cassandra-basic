package com.example.persist.shared;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

@Entity
@Cacheable(true)
@Table("users")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User {

    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private Long id;

    @Column("fname")
    private String firstName;

    @Column("lname")
    private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public int hashCode() {
    	return id == null ? 0 : id.hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (o instanceof User) {
            User u = (User) o;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(u.getId(), id);
            return builder.isEquals();
        }
        return false;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
