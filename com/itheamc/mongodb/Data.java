package com.itheamc.mongodb;

import org.bson.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private String _id;
    private String _title;
    private String _desc;
    private List<String> _tags;
    private String _url;
    private String _added_by;
    private Document _document;

    // Constructor
    public Data() {
    }

    // Constructor with parameters
    public Data(String _title, String _desc, List<String> _tags, String _url, String _added_by) {
        this._title = _title;
        this._desc = _desc;
        this._tags = _tags;
        this._url = _url;
        this._added_by = _added_by;
        this._document = new Document();
    }

    // Getters and Setters

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_desc() {
        return _desc;
    }

    public void set_desc(String _desc) {
        this._desc = _desc;
    }

    public List<String> get_tags() {
        return _tags;
    }

    public void set_tags(List<String> _tags) {
        this._tags = _tags;
    }

    public String get_url() {
        return _url;
    }

    public void set_url(String _url) {
        this._url = _url;
    }

    public String get_added_by() {
        return _added_by;
    }

    public void set_added_by(String _added_by) {
        this._added_by = _added_by;
    }

    public Document get_document() {
        _document.append("_id", new BsonObjectId(ObjectId.get()));
        _document.append("_title", new BsonString(_title));
        _document.append("_desc", new BsonString(_title));
        _document.append("_tags", new BsonArray(bsonStrings(_tags)));
        _document.append("_url", new BsonString(_url));
        _document.append("_added_by", new BsonString(_added_by));
        _document.append("__v", new BsonInt32(0));
        return _document;
    }

    private List<BsonString> bsonStrings(List<String> strings) {
        List<BsonString> bsonStrings = new ArrayList<>();
        for (String s: strings) {
            bsonStrings.add(new BsonString(s));
        }

        return bsonStrings;
    }
}
