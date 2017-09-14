/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.esports.aeq.ts3.bot.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents a hierarchical role that may be mapped to different server groups.
 * <p>
 * Each role may define a parent, which should have more permissions than this role and multiple children, which should
 * have less permissions than this role.
 * <p>
 * This wrapper primarily consists to provide an additional layer of abstraction and prevent checking for specific
 * server groups.
 *
 * @author Lukas Kannenberg
 * @version 1.0
 * @since 13.09.2017
 */
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private long id;

    /**
     * The unique name of this role.
     */
    @Column(name = "name")
    private String name;

    /**
     * Parent roles. (stronger than this role)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role parent;

    /**
     * Children of this role. (weaker than this role)
     */
    @OneToMany
    @OrderColumn
    @JoinColumn(name = "role_id")
    private Set<Role> children;

    /**
     * A list that holds all server groups that belong to this role.
     */
    @ElementCollection
    @CollectionTable(name = "aeq_role_server_groups", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "nickname")
    private Set<Integer> serverGroups;

    public Role(String name, Role parent, Set<Role> children, Set<Integer> serverGroups) {
        this.name = name;
        this.parent = parent;
        this.children = children;
        this.serverGroups = serverGroups;
    }

    /**
     * @return the id of this role
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id of this role
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name of this role
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name of this role
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the parent of this role
     */
    public Role getParent() {
        return parent;
    }

    /**
     * @param parent the parent of this role
     */
    public void setParent(Role parent) {
        this.parent = parent;
    }

    /**
     * @return the children of this role
     */
    public Set<Role> getChildren() {
        return children;
    }

    /**
     * @param children the children of this role
     */
    public void setChildren(Set<Role> children) {
        this.children = children;
    }

    /**
     * @return the server groups mapped to this role
     */
    public Set<Integer> getServerGroups() {
        return serverGroups;
    }

    /**
     * @param serverGroups the server groups mapped to this role
     */
    public void setServerGroups(Set<Integer> serverGroups) {
        this.serverGroups = serverGroups;
    }
}
