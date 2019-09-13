CREATE TABLE IF NOT EXISTS Location (
    woeid       INTEGER  PRIMARY KEY COMMENT 'identifier',
    version     INTEGER  NOT NULL    COMMENT 'hibernate controller field',
    city        VARCHAR(50)          COMMENT 'city name (string)',
    region      VARCHAR(100)         COMMENT 'state, territory, or region, if given (string)',
    country     VARCHAR(50)           COMMENT 'two-character country code. (string)',
    lat         DOUBLE               COMMENT 'The latitude of the location',
    lon         DOUBLE               COMMENT 'The longitude of the location',
    timezone_id VARCHAR(50)          COMMENT 'Timezone'
)
COMMENT 'Location';

CREATE TABLE IF NOT EXISTS Current_observation (
    id              INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT 'identifier',
    version         INTEGER NOT NULL,
    pub_date        VARCHAR (50)                       COMMENT 'The date and time this forecast was posted, in the date format defined by RFC822 Section 5, for example Mon, 25 Sep 17:25:18 -0700',
    location_id     INTEGER                            COMMENT 'Location identifier' REFERENCES Location (woeid) ON DELETE CASCADE ON UPDATE CASCADE
)
COMMENT 'Current weather';

CREATE TABLE IF NOT EXISTS Wind (
    cur_obs_id  INTEGER  PRIMARY KEY COMMENT 'current observation identifier' REFERENCES Current_observation (id) ON DELETE CASCADE ON UPDATE CASCADE,
    version     INTEGER  NOT NULL,
    chill       INTEGER              COMMENT 'wind chill in degrees (integer)',
    direction   INTEGER              COMMENT 'wind direction, in degrees (integer)',
    speed       INTEGER              COMMENT 'wind speed, in the units specified in the speed attribute of the yweather:units element (m/h or km/h). (integer)'
)
COMMENT 'Forecast information about wind';

CREATE TABLE IF NOT EXISTS Atmosphere (
    cur_obs_id  INTEGER  PRIMARY KEY COMMENT 'current observation identifier' REFERENCES Current_observation (id) ON DELETE CASCADE ON UPDATE CASCADE,
    version     INTEGER  NOT NULL,
    humidity    INTEGER              COMMENT 'humidity, in percentage (integer)',
    visibility  FLOAT                COMMENT 'visibility, in the units specified by the distance attribute of the yweather:units element (mile or km)',
    pressure    FLOAT                COMMENT 'pressure: barometric pressure, in the units specified by the pressure attribute of the yweather:units element (inchHg or mbar). (float)',
    rising      INTEGER              COMMENT 'rising: state of the barometric pressure: steady (0), rising (1), or falling (2). (integer: 0, 1, 2)'
)
COMMENT 'Forecast information about current atmospheric pressure, humidity, and visibility';

CREATE TABLE IF NOT EXISTS Astronomy (
    cur_obs_id  INTEGER  PRIMARY KEY COMMENT 'current observation identifier' REFERENCES Current_observation (id) ON DELETE CASCADE ON UPDATE CASCADE,
    version     INTEGER  NOT NULL,
    sunrise     VARCHAR(10)          COMMENT 'sunrise: today''s sunrise time. The time is a string in a local time format of "h:mm am/pm", for example "7:02 am" (string)',
    sunset      VARCHAR(10)          COMMENT 'sunset: today''s sunset time. The time is a string in a local time format of "h:mm am/pm", for example "4:51 pm" (string)'
)
COMMENT 'Forecast information about current astronomical conditions';

CREATE TABLE IF NOT EXISTS Conditio (
    cur_obs_id  INTEGER  PRIMARY KEY COMMENT 'current observation identifier' REFERENCES Current_observation (id) ON DELETE CASCADE ON UPDATE CASCADE,
    version     INTEGER  NOT NULL,
    text        VARCHAR(25)          COMMENT 'a textual description of conditions, for example, "Partly Cloudy" (string)',
    code        INTEGER              COMMENT 'the condition code for this forecast. You could use this code to choose a text description or image for the forecast. The possible values for this element are described in Condition Codes (integer)',
    temperature INTEGER              COMMENT 'the current temperature, in the units specified by the yweather:units element (integer)'
)
COMMENT 'The current weather conditions';

CREATE TABLE IF NOT EXISTS Forecast (
    id          INTEGER  PRIMARY KEY AUTO_INCREMENT COMMENT '77711',
    version     INTEGER NOT NULL,
    day         VARCHAR(3)           COMMENT 'day of the week to which this forecast applies. Possible values are Mon Tue Wed Thu Fri Sat Sun (string)',
    date        VARCHAR(20)          COMMENT 'the date to which this forecast applies. The date is in "dd Mmm yyyy" format, for example "30 Nov 2005" (string)',
    low         INTEGER              COMMENT 'the forecasted low temperature for this day, in the units specified by the yweather:units element (integer)',
    high        INTEGER              COMMENT 'the forecasted high temperature for this day, in the units specified by the yweather:units element (integer)',
    text        VARCHAR(50)          COMMENT 'a textual description of conditions, for example, "Partly Cloudy" (string)',
    code        INTEGER              COMMENT 'the condition code for this forecast. You could use this code to choose a text description or image for the forecast. The possible values for this element are described in Condition Codes (integer)',
    location_id INTEGER              COMMENT 'Location identifier' REFERENCES Location (woeid) ON DELETE CASCADE ON UPDATE CASCADE
)
COMMENT 'The weather forecast for a specific day';

CREATE INDEX IX_Location_City ON Location (city);