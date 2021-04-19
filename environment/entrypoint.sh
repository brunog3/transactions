java $JAVA_OPTS $JAVA_AGENT -jar transactions/app.jar \
	--spring.datasource.url="$DATASOURCE_URL" \
	--spring.datasource.username="$DATASOURCE_USERNAME" \
	--spring.datasource.password="$DATASOURCE_PASSWORD" \
