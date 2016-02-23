package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;

//@Configuration
public class CassandraConfiguration {

	private String getKeyspaceName() {
		return "mykeyspace";
	}

	private String getContactPoints() {
		return "10.20.133.25,10.20.133.119";
	}

	private Integer getPortNumber() {
		return 9042;
	}

	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints(getContactPoints());
		cluster.setPort(getPortNumber());
		return cluster;
	}

	@Bean
	public CassandraMappingContext mappingContext() {
		return new BasicCassandraMappingContext();
	}

	@Bean
	public CassandraConverter converter() {
		return new MappingCassandraConverter(mappingContext());
	}

	@Bean
	public CassandraSessionFactoryBean session() throws Exception {
		CassandraSessionFactoryBean cassandraSessionFactoryBean = new CassandraSessionFactoryBean();
		cassandraSessionFactoryBean.setCluster(cluster().getObject());
		cassandraSessionFactoryBean.setKeyspaceName(getKeyspaceName());
		cassandraSessionFactoryBean.setConverter(converter());
		cassandraSessionFactoryBean.setSchemaAction(SchemaAction.NONE);
		return cassandraSessionFactoryBean;
	}

	@Bean
	public CassandraOperations cassandraTemplate() throws Exception {
		return new CassandraTemplate(session().getObject());
	}
}
