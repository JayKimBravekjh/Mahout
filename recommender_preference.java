# setting preference values in a preferenceArray

PreferenceArray user1Prefs = new GenericUserPreferenceArray(2);

user1Prefs.setUserID(0, 1L); 

user1Prefs.setItemID(0, 101L);
user1Prefs.setValue(0, 2.0f);

# Expresses preferences 
user1Prefs.setItemID(1, 102L);
user1Prefs.setValue(1, 3.0f);

# Materializes Preference for item 102
Preference pref = user1Prefs.get(1);
