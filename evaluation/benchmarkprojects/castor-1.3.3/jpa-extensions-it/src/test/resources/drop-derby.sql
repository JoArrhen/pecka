DROP TABLE identity_generator;
DROP TABLE generator_table;
DROP TABLE auto_annotated_class;
DROP TABLE annotated_class_with_default_table_generator_definition;
DROP TABLE annotated_field_with_default_table_generator_definition;
DROP TABLE table_generator_field_subject;
DROP TABLE table_generator_table_subject;
DROP TABLE table_key_generator;
DROP SEQUENCE TEST.class_sequence_with_default_name RESTRICT;
DROP TABLE sequence_generator_class_with_default_sequence_name;
DROP SEQUENCE TEST.field_sequence_with_default_name RESTRICT;
DROP TABLE TEST.sequence_generator_field_with_default_sequence_name;
DROP SEQUENCE TEST.class_sequence RESTRICT;
DROP TABLE sequence_generator_class;
DROP SEQUENCE TEST.field_sequence RESTRICT;
DROP TABLE TEST.sequence_generator_field;
DROP SEQUENCE TEST.non_cached_version_sequence RESTRICT;
DROP SEQUENCE TEST.version_sequence RESTRICT;
DROP TABLE non_cached_version;
DROP TABLE version;
DROP TABLE TEST.Cache_none;
DROP TABLE TEST.Cache_limited;
DROP TABLE TEST.Cache_unlimited;
DROP TABLE TEST.MappedSuperclass_keyboard;
DROP TABLE TEST.NamedQueries_person;
DROP TABLE TEST.NamedQueries_employee
DROP TABLE TEST.NamedNativeQueries_student;
DROP TABLE TEST.Callbacks_person;
DROP TABLE TEST.Callbacks_cat;
DROP TABLE TEST.Callbacks_martian;
DROP TABLE TEST.EntityListeners_animal;
DROP TABLE TEST.EntityListeners_pet;
DROP TABLE TEST.EntityListeners_dog;
DROP TABLE TEST.EntityListeners_retriever;
DROP TABLE TEST.ExcludeListeners_foo;
DROP TABLE TEST.ExcludeListeners_bar;
DROP TABLE TEST.Temporal_person;
DROP TABLE TEST.Lob_entity;
DROP TABLE TEST.Enum_entity;
DROP TABLE TEST.single_staff;
DROP TABLE TEST.staff_version;
DROP TABLE TEST.OneToOne_show;
DROP TABLE TEST.OneToOne_timeslot;
DROP TABLE TEST.OneToOne_rating;
DROP TABLE TEST.OneToMany_actor;
DROP TABLE TEST.OneToMany_role;
DROP TABLE TEST.ManyToMany_author;
DROP TABLE TEST.ManyToMany_book;
DROP TABLE TEST.ManyToMany_books_authors;
DROP TABLE TEST.Cascading_parent;
DROP TABLE TEST.Cascading_child;
DROP TABLE TEST.Inheritance_Big_Tree;
DROP TABLE TEST.Inheritance_Tree;
DROP TABLE TEST.Inheritance_Plant;

DROP SCHEMA TEST;
