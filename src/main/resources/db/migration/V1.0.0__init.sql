CREATE SCHEMA IF NOT EXISTS charging_stations;
CREATE EXTENSION IF NOT EXISTS postgis;

CREATE SEQUENCE charging_stations.charging_stations_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS charging_stations.charging_stations
(
    id          VARCHAR PRIMARY KEY,
    coordinates    geography(POINT),
    postal_code VARCHAR
);

CREATE INDEX location_idx ON charging_stations.charging_stations USING GIST (coordinates);
CREATE INDEX postal_code_idx ON charging_stations.charging_stations USING btree (postal_code);

